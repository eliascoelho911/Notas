package br.com.eliascoelho911.notas.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.eliascoelho911.notas.exception.AppBarLayoutSemToolbar
import br.com.eliascoelho911.notas.exception.ViewRootNaoEhAppBarLayout
import com.google.android.material.appbar.AppBarLayout
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CriadorDeAppBarLayoutTest {
    @Mock
    private lateinit var inflater: LayoutInflater

    @Mock
    private lateinit var parent: ViewGroup

    @Mock
    private lateinit var appBarLayout: AppBarLayout

    private lateinit var criadorAppBar: CriadorDeAppBarLayout

    @Before
    fun criaCriador() {
        criadorAppBar = spy(CriadorDeAppBarLayout(inflater, parent))
    }

    @Test
    fun deve_CriarAppBarLayout_QuandoOLayoutEhValido() {
        val layout = 0
        doNothing().`when`(criadorAppBar).substituiAtualAppBarLayoutPor(appBarLayout)
        doNothing().`when`(criadorAppBar).valida(appBarLayout)
        doReturn(appBarLayout).`when`(criadorAppBar).inflaLayout(layout)
        val viewCriada = criadorAppBar.criarEExibir(layout)
        assertEquals(viewCriada, appBarLayout)
    }

    @Test(expected = ViewRootNaoEhAppBarLayout::class)
    fun naodeve_CriarAppBarLayout_QuandoAViewRootNaoEhUmAppBarLayout() {
        val view = mock(View::class.java)
        doReturn(view).`when`(criadorAppBar).inflaLayout(0)
        criadorAppBar.criarEExibir(0)
    }

    @Test(expected = AppBarLayoutSemToolbar::class)
    fun naodeve_CriarAppBarLayout_QuandoNaoTemToolbar() {
        doReturn(appBarLayout).`when`(criadorAppBar).inflaLayout(0)
        criadorAppBar.criarEExibir(0)
    }
}
