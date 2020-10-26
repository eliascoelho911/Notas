package br.com.eliascoelho911.notas.ui.notas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.ui.setupWithNavController
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_notas.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotasFragment : Fragment() {

    private val listaDeNotas by lazy {
        fragment_notas_lista_de_notas
    }
    private val viewModel: NotasViewModel by viewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()
    private val manipuladorDeListaDeNotas: ManipuladorDeListaDeNotas by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraNotaBarComNavController()
        exibeToolbar()
        criaListaDeNotas()
        buscaNotas()
    }

    private fun configuraNotaBarComNavController() {
        fragment_notas_nova_nota_bar.setupWithNavController(mainViewModel.navControlller)
    }

    private fun exibeToolbar() {
        mainViewModel.toolbar(
            R.layout.toolbar_arredondada,
            R.id.toolbar_arredondada_toolbar,
            setOf(R.id.nav_notas)
        )
    }

    private fun criaListaDeNotas() {
        manipuladorDeListaDeNotas.criaLista(listaDeNotas)
    }

    private fun buscaNotas() {
        viewModel.todas { livedata ->
            livedata.observe(viewLifecycleOwner, { notas ->
                manipuladorDeListaDeNotas.atualizaLista(listaDeNotas, notas)
            })
        }
    }
}