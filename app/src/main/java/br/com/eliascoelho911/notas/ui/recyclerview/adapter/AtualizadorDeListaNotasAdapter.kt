package br.com.eliascoelho911.notas.ui.recyclerview.adapter

import br.com.eliascoelho911.notas.model.NotaCompleta

open class AtualizadorDeListaNotasAdapter(
    private val adapter: ListaNotasAdapter,
) {
    fun atualiza(notasAntigas: List<NotaCompleta>, notasNovas: List<NotaCompleta>) {
        if (notasNovas.size - notasAntigas.size == 1)
            if (notaAdicionada(notasAntigas, notasNovas)) return
        if (notasNovas.size == notasAntigas.size)
            if (notaAlterada(notasAntigas, notasNovas)) return
        if (notasAntigas.size - notasNovas.size == 1)
            if (notaRemovida(notasAntigas, notasNovas)) return
        notificarAlteracaoNaLista()
    }

    open fun notificarAlteracaoNaLista() {
        adapter.notifyDataSetChanged()
    }

    open fun notaAdicionada(
        notasAntigas: List<NotaCompleta>,
        notasNovas: List<NotaCompleta>,
    ): Boolean {
        if (!notasAntigas.any { it.nota.id == notasNovas[0].nota.id }) {
            notificarAdapterItemInserido(0)
            return true
        }
        return false
    }

    open fun notificarAdapterItemInserido(position: Int) {
        adapter.notifyItemInserted(position)
    }

    open fun notaRemovida(
        notasAntigas: List<NotaCompleta>,
        notasNovas: List<NotaCompleta>,
    ): Boolean {
        notasAntigas.forEachIndexed { index, nota ->
            if (!notasNovas.any { nota.nota.id == it.nota.id }) {
                notificarAdapterItemRemovido(index)
                return true
            }
        }
        return false
    }

    open fun notificarAdapterItemRemovido(position: Int) {
        adapter.notifyItemRemoved(position)
    }

    open fun notaAlterada(
        notasAntigas: List<NotaCompleta>,
        notasNovas: List<NotaCompleta>,
    ): Boolean {
        var algumaNotaAlterada = false
        notasNovas.forEachIndexed { index, nota ->
            if (!notasAntigas.contains(nota) && notasAntigas.any { nota.nota.id == it.nota.id }) {
                notificarAdapterItemAlterado(index)
                algumaNotaAlterada = true
            }
        }
        return algumaNotaAlterada
    }

    open fun notificarAdapterItemAlterado(position: Int) {
        adapter.notifyItemChanged(position)
    }

}