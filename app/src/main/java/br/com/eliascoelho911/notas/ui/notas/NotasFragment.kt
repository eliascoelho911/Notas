package br.com.eliascoelho911.notas.ui.notas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.ui.main.MainViewModel
import br.com.eliascoelho911.notas.ui.recyclerview.adapter.ListaNotasAdapter
import kotlinx.android.synthetic.main.fragment_notas.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotasFragment : Fragment() {
    private val viewModel: NotasViewModel by viewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()
    private val navController by lazy {
        findNavController()
    }
    private val listaNotasAdapter: ListaNotasAdapter by lazy {
        ListaNotasAdapter { nota ->
            val navNotasParaNavFormulario = NotasFragmentDirections.navNotasParaNavFormulario(nota)
            navController.navigate(navNotasParaNavFormulario)

        }
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
        mainViewModel.propriedadesFab.configura(true) {
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
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        fragment_notas_lista_de_notas.layoutManager = layoutManager
        fragment_notas_lista_de_notas.adapter = listaNotasAdapter
    }

    private fun buscaNotas() {
        viewModel.todas { livedata ->
            livedata.observe(viewLifecycleOwner, { notas ->
                listaNotasAdapter.submitList(notas)
            })
        }
    }
}
