package br.com.eliascoelho911.notas.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.appbar.AppBarLayout
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CriadorDeToolbarTest {
    @Mock
    private lateinit var inflater: LayoutInflater

    @Mock
    private lateinit var parent: ViewGroup

    @Mock
    private lateinit var appBarLayout: AppBarLayout

    private lateinit var criador: CriadorDeToolbar

    @Before
    fun criaCriador() {
        criador = spy(CriadorDeToolbar(inflater, parent))
    }

    @Test
    fun deve_CriarUmaToolbar_QuandoOLayoutEhValido() {
        val layout = 0
        doNothing().`when`(criador).exibe(appBarLayout)
        doReturn(appBarLayout).`when`(criador).inflaLayout(layout)
        val viewCriada = criador.criarEExibir(layout)
        assertEquals(viewCriada, appBarLayout)
    }
    @Test(expected = IllegalArgumentException::class)
    fun naodeve_CriarUmaToolbar_QuandoAViewRootNaoEhUmAppBarLayout() {
        val view = mock(View::class.java)
        doReturn(view).`when`(criador).inflaLayout(0)
        criador.criarEExibir(0)
    }
}