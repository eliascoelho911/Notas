package br.com.eliascoelho911.notas.ui.marcadores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.model.Marcador
import br.com.eliascoelho911.notas.model.NotaCompleta
import br.com.eliascoelho911.notas.ui.main.MainViewModel
import br.com.eliascoelho911.notas.ui.recyclerview.adapter.MarcadoresAdapter
import kotlinx.android.synthetic.main.fragment_marcadores.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarcadoresFragment : Fragment() {
    private val viewModel: MarcadoresViewModel by viewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()
    private val args: MarcadoresFragmentArgs by navArgs()
    private val adapter = MarcadoresAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        configuraMainViewModel()
        return inflater.inflate(R.layout.fragment_marcadores, container, false)
    }

    private fun configuraMainViewModel() {
        mainViewModel.configuracaoPadrao()
        modificaToolbar()
    }

    private fun modificaToolbar() {
        val propriedadesToolbar = mainViewModel.propriedadesToolbar
        propriedadesToolbar.esconderAoMoverScroll(b = false)
        val toolbarEditText = propriedadesToolbar.toolbarEditText
        toolbarEditText.altera(getString(R.string.digite_o_nome_do_marcador))
        toolbarEditText.limpaTexto()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buscaMarcadores()
        mainViewModel.propriedadesToolbar.toolbarEditText.text.observe(viewLifecycleOwner) {

        }
    }

    private fun buscaMarcadores() {
        viewModel.todos {
            it.observe(viewLifecycleOwner) { marcadores ->
                fragment_marcadores_lista.adapter = adapter
                atualizaAdapter(marcadores)
            }
        }
    }

    private fun atualizaAdapter(marcadores: List<Marcador>) {
        adapter.onClick = { marcadorSelecionado ->
            val notaCompleta =
                criaNovaNotaCompletaComOMarcadorSelecionado(marcadorSelecionado)
            val actionNavMarcadoresToNavFormulario =
                MarcadoresFragmentDirections.navMarcadoresParaNavFormulario(notaCompleta)
            findNavController().navigate(actionNavMarcadoresToNavFormulario)
        }
    }

    private fun criaNovaNotaCompletaComOMarcadorSelecionado(marcador: Marcador): NotaCompleta {
        return NotaCompleta(args.nota.nota, marcador)
    }
}