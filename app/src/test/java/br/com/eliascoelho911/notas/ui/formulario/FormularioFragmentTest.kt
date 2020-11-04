package br.com.eliascoelho911.notas.ui.formulario

import androidx.fragment.app.FragmentManager
import br.com.eliascoelho911.notas.model.Nota
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
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
        val nota = Nota(cor = 0)
        doReturn(nota).`when`(fragment).criaNota()
        fragment.salvaNotaSeAlgumCampoEstiverPreenchido(viewModel)
        verify(fragment).salvaNota(nota, viewModel)
        verify(viewModel).salvar(nota)
    }

    @Test
    fun naoDeve_SalvarNota_QuandoAlgumCampoNaoEstiverPreenchido() {
        doReturn(false).`when`(fragment).algumCampoEstiverPreenchido()
        fragment.salvaNotaSeAlgumCampoEstiverPreenchido(viewModel)
        verify(fragment, never()).salvaNota(Nota(cor = 0), viewModel)
    }

    @Test
    fun deve_ExibirBottomSheetAdicionar_QuandoClicarNoBotaoAdicionar() {
        val bottomSheetAdicionar = mock<BottomSheetAdicionar>()
        val fragmentManager = mock<FragmentManager>()
        fragment.exibeBottomSheetAdicionar(bottomSheetAdicionar, fragmentManager)
        verify(bottomSheetAdicionar).show(fragmentManager, null)
    }

    @Test
    fun deve_ExibirBottomSheetOpcoes_QuandoClicarNoBotaoOpcoes() {
        val bottomSheetOpcoes = mock<BottomSheetOpcoes>()
        val fragmentManager = mock<FragmentManager>()
        fragment.exibeBottomSheetOpcoes(bottomSheetOpcoes, fragmentManager)
        verify(bottomSheetOpcoes).show(fragmentManager, null)
    }
}