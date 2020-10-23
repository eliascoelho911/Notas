package br.com.eliascoelho911.notas.ui.notas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.model.Nota
import br.com.eliascoelho911.notas.ui.recyclerview.adapter.ListaNotasAdapter
import kotlinx.android.synthetic.main.fragment_notas.*
import org.koin.android.ext.android.inject

class NotasFragment : Fragment() {

    private val listaDeNotas by lazy{
        fragment_notas_lista_de_notas
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}