package br.com.eliascoelho911.notas.ui.notas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.eliascoelho911.notas.R
import kotlinx.android.synthetic.main.fragment_notas.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotasFragment : Fragment() {

    private val listaDeNotas by lazy {
        fragment_notas_lista_de_notas
    }
    private val viewModel: NotasViewModel by viewModel()
    private val manipuladorDeListaDeNotas = ManipuladorDeListaDeNotas()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        criaListaDeNotas()
        buscaNotas()
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