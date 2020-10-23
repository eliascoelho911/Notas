package br.com.eliascoelho911.notas.ui.notas

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.eliascoelho911.notas.model.Nota
import br.com.eliascoelho911.notas.ui.recyclerview.adapter.ListaNotasAdapter

open class ManipuladorDeListaDeNotas {
    fun criaLista(recyclerView: RecyclerView) {
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ListaNotasAdapter()
    }

    fun atualizaLista(recyclerView: RecyclerView, notas: List<Nota>) {
        val adapter: ListaNotasAdapter = recyclerView.adapter as ListaNotasAdapter
        adapter.atualiza(notas)
    }
}