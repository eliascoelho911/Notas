package br.com.eliascoelho911.notas.ui.notas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_notas.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotasFragment : Fragment() {
    private val viewModel: NotasViewModel by viewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()
    private val manipuladorDeListaDeNotas: ManipuladorDeListaDeNotas by inject()
    private val navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        configuraMainViewModel()
        return inflater.inflate(R.layout.fragment_notas, container, false)
    }

    private fun configuraMainViewModel() {
        mainViewModel.propriedadesFab.configura(visivel = VISIBLE) {
            val navNotasParaNavFormulario = NotasFragmentDirections.navNotasParaNavFormulario()
            navController.navigate(navNotasParaNavFormulario)
        }
        mainViewModel.propriedadesBottomAppBar.alteraVisibilidade(VISIBLE)
        mainViewModel.propriedadesToolbar.esconderAoMoverScroll(b = true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        criaListaDeNotas()
        buscaNotas()
    }

    private fun criaListaDeNotas() {
        manipuladorDeListaDeNotas.criaLista(fragment_notas_lista_de_notas) { nota ->
            val navNotasParaNavFormulario = NotasFragmentDirections.navNotasParaNavFormulario(nota)
            navController.navigate(navNotasParaNavFormulario)
        }
    }

    private fun buscaNotas() {
        viewModel.todas { livedata ->
            livedata.observe(viewLifecycleOwner, { notas ->
                manipuladorDeListaDeNotas.atualizaLista(fragment_notas_lista_de_notas, notas)
            })
        }
    }
}
