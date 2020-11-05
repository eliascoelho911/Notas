package br.com.eliascoelho911.notas.ui.recyclerview.adapter

import br.com.eliascoelho911.notas.model.Nota
import br.com.eliascoelho911.notas.model.NotaCompleta
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AtualizadorDeListaNotasAdapterTest {
    companion object {
        private val NOTA_0 = NotaCompleta(Nota(id = 0, titulo = "0", descricao = "0", cor = 0))
        private val NOTA_1 = NotaCompleta(Nota(id = 1, titulo = "1", descricao = "1", cor = 1))
        private val NOTA_2 = NotaCompleta(Nota(id = 2, titulo = "2", descricao = "2", cor = 2))
    }

    private val atualizador = spy(AtualizadorDeListaNotasAdapter(mock()))

    @Test
    fun deve_AdicionarNaLista_QuandoAdicionarUmItem() {
        val notasAntigas = listOf(NOTA_0)
        val notasNovas = listOf(NOTA_1, NOTA_0)
        doNothing().`when`(atualizador).notificarAdapterItemInserido(any())
        atualizador.atualiza(notasAntigas, notasNovas)
        verify(atualizador, never()).notaAlterada(notasAntigas, notasNovas)
        verify(atualizador, never()).notaRemovida(notasAntigas, notasNovas)
        verify(atualizador, never()).notificarAlteracaoNaLista()
        verify(atualizador).notaAdicionada(notasAntigas, notasNovas)
        assertEquals(atualizador.notaAdicionada(notasAntigas, notasNovas), true)
    }

    @Test
    fun deve_AtualizarItemDaLista_QuandoAlteradoUmItem() {
        val notasAntigas = listOf(NOTA_0)
        val notasNovas = listOf(NotaCompleta(Nota(id = 0, titulo = "1", descricao = "1", cor = 1)))
        doNothing().`when`(atualizador).notificarAdapterItemAlterado(any())
        atualizador.atualiza(notasAntigas, notasNovas)
        verify(atualizador, never()).notaAdicionada(notasAntigas, notasNovas)
        verify(atualizador, never()).notaRemovida(notasAntigas, notasNovas)
        verify(atualizador, never()).notificarAlteracaoNaLista()
        verify(atualizador).notaAlterada(notasAntigas, notasNovas)
        assertEquals(atualizador.notaAlterada(notasAntigas, notasNovas), true)
    }

    @Test
    fun deve_RemoverItemDaLista_QuandoRemovidoUmItem() {
        val notasAntigas = listOf(NOTA_0, NOTA_1)
        val notasNovas = listOf(NOTA_0)
        doNothing().`when`(atualizador).notificarAdapterItemRemovido(any())
        atualizador.atualiza(notasAntigas, notasNovas)
        verify(atualizador, never()).notaAdicionada(notasAntigas, notasNovas)
        verify(atualizador, never()).notaAlterada(notasAntigas, notasNovas)
        verify(atualizador, never()).notificarAlteracaoNaLista()
        verify(atualizador).notaRemovida(notasAntigas, notasNovas)
        assertEquals(atualizador.notaRemovida(notasAntigas, notasNovas), true)
    }

    @Test
    fun deve_AtualizarTodaLista_QuandoAlteradoVariosItensDaLista() {
        val notasAntigas = listOf(NOTA_0)
        val notasNovas = listOf(NOTA_0, NOTA_1, NOTA_2)
        doNothing().`when`(atualizador).notificarAlteracaoNaLista()
        atualizador.atualiza(notasAntigas, notasNovas)
        verify(atualizador, never()).notaAdicionada(notasAntigas, notasNovas)
        verify(atualizador, never()).notaAlterada(notasAntigas, notasNovas)
        verify(atualizador, never()).notaRemovida(notasAntigas, notasNovas)
        verify(atualizador).notificarAlteracaoNaLista()
    }
}