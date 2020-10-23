package br.com.eliascoelho911.notas.ui.notas

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.eliascoelho911.notas.ui.recyclerview.adapter.ListaNotasAdapter

class CriadorDeListaDeNotas {
    fun criaLista(recyclerView: RecyclerView, adapter: ListaNotasAdapter) {
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}