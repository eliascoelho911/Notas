package br.com.eliascoelho911.notas.ui.formulario

import br.com.eliascoelho911.notas.model.Nota
import br.com.eliascoelho911.notas.model.Texto
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FormularioFragmentTest {
    @Spy
    private val fragment = FormularioFragment()

    @Mock
    private lateinit var viewModel: FormularioViewModel

    @Test
    fun deve_SalvarNota_QuandoAlgumCampoEstiverPreenchido() {
        doReturn(true).`when`(fragment).algumCampoEstiverPreenchido()
        val nota = Nota(titulo = "teste", descricao = Texto("teste"))
        doReturn(nota).`when`(fragment).criaNota()
        fragment.salvaNotaSeAlgumCampoEstiverPreenchido(viewModel)
        verify(fragment).salvaNota(nota, viewModel)
        verify(viewModel).salvar(nota)
    }
    @Test
    fun naoDeve_SalvarNota_QuandoAlgumCampoNaoEstiverPreenchido() {
        doReturn(false).`when`(fragment).algumCampoEstiverPreenchido()
        fragment.salvaNotaSeAlgumCampoEstiverPreenchido(viewModel)
        verify(fragment, never()).salvaNota(Nota(), viewModel)
    }
}