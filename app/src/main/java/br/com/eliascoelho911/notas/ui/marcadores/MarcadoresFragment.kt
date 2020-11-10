package br.com.eliascoelho911.notas.ui.marcadores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.databinding.FragmentMarcadoresBinding
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
    private val adapter by lazy {
        MarcadoresAdapter { marcadorSelecionado ->
            vaiParaFormulario(marcadorSelecionado)
        }
    }

    private fun vaiParaFormulario(marcadorSelecionado: Marcador) {
        val notaCompleta =
            criaNovaNotaCompletaComOMarcadorSelecionado(marcadorSelecionado)
        val actionNavMarcadoresToNavFormulario =
            MarcadoresFragmentDirections.navMarcadoresParaNavFormulario(notaCompleta)
        findNavController().navigate(actionNavMarcadoresToNavFormulario)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        configuraMainViewModel()
        return getBinding(inflater, container).root
    }

    private fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentMarcadoresBinding {
        return FragmentMarcadoresBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@MarcadoresFragment.viewLifecycleOwner
            val textToolbar = mainViewModel.propriedadesToolbar.toolbarEditText.text
            editTextToolbar = textToolbar
            acaoCriarMarcador = View.OnClickListener {
                textToolbar.value?.run {
                    salvaMarcadorEVaiParaFormulario(this)
                }
            }
        }
    }

    private fun salvaMarcadorEVaiParaFormulario(text: String) {
        viewModel.salva(Marcador(nome = text)) {
            vaiParaFormulario(Marcador(nome = text, id = it))
        }
    }

    private fun configuraMainViewModel() {
        mainViewModel.configuracaoPadrao()
        modificaToolbar()
    }

    private fun modificaToolbar() {
        val propriedadesToolbar = mainViewModel.propriedadesToolbar
        propriedadesToolbar.esconderAoMoverScroll(false)
        val toolbarEditText = propriedadesToolbar.toolbarEditText
        toolbarEditText.altera(getString(R.string.digite_o_nome_do_marcador))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buscaMarcadores()
        colocaAdapterNaLista()
        mainViewModel.propriedadesToolbar.toolbarEditText.text.observe(viewLifecycleOwner) {
            filtraLista(it)
        }
    }

    private fun filtraLista(it: String) {
        if (it.isBlank())
            adapter.limpaFiltro()
        else
            adapter.filtra(it)
    }

    private fun colocaAdapterNaLista() {
        fragment_marcadores_lista.adapter = adapter
    }

    private fun buscaMarcadores() {
        viewModel.todos {
            it.observe(viewLifecycleOwner) { marcadores ->
                adapter.atualiza(marcadores)
            }
        }
    }

    private fun criaNovaNotaCompletaComOMarcadorSelecionado(marcador: Marcador): NotaCompleta {
        return NotaCompleta(args.nota.nota, marcador)
    }
}