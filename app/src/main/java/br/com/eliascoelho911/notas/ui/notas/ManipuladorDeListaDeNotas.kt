package br.com.eliascoelho911.notas.ui.notas

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.eliascoelho911.notas.model.NotaCompleta
import br.com.eliascoelho911.notas.ui.recyclerview.adapter.ListaNotasAdapter

open class ManipuladorDeListaDeNotas {
    fun criaLista(recyclerView: RecyclerView, onClickItem: (nota: NotaCompleta) -> Unit) {
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ListaNotasAdapter(onClickItem = onClickItem)
    }

    fun atualizaLista(recyclerView: RecyclerView, notas: List<NotaCompleta>) {
        val adapter: ListaNotasAdapter = recyclerView.adapter as ListaNotasAdapter
        adapter.atualiza(notas)
    }
}