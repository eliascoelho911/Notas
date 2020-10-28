package br.com.eliascoelho911.notas.ui.formulario

import br.com.eliascoelho911.notas.model.Nota
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.verification.VerificationMode

@RunWith(MockitoJUnitRunner::class)
class FormularioFragmentTest {
    @Spy
    private val fragment = FormularioFragment()

    @Test
    fun deve_SalvarNota_QuandoAlgumCampoEstiverPreenchido() {
        doReturn(true).`when`(fragment).algumCampoEstiverPreenchido()
        val nota = Nota(titulo = "titulo", descricao = "descricao")
        doReturn(nota).`when`(fragment).criaNota()
        doNothing().`when`(fragment).salvaNota(nota)
        fragment.salvaNotaSeAlgumCampoEstiverPreenchido()
        verify(fragment).salvaNota(nota)
    }
    @Test
    fun naoDeve_SalvarNota_QuandoAlgumCampoNaoEstiverPreenchido() {
        doReturn(false).`when`(fragment).algumCampoEstiverPreenchido()
        fragment.salvaNotaSeAlgumCampoEstiverPreenchido()
        verify(fragment, never()).salvaNota(Nota())
    }
}