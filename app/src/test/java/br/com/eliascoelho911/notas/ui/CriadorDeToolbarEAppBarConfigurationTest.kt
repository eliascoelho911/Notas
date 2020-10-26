package br.com.eliascoelho911.notas.ui

import com.google.android.material.appbar.AppBarLayout
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.lang.IllegalArgumentException

@RunWith(MockitoJUnitRunner::class)
class CriadorDeToolbarEAppBarConfigurationTest {
    @Mock
    private lateinit var criadorDeAppBarConfiguration: CriadorDeAppBarConfiguration

    @Mock
    private lateinit var criadorDeToolbar: CriadorDeToolbar

    @Test(expected = IllegalArgumentException::class)
    fun naodeve_RetornarToolbarEAppBarConfiguration_QuandoParametrosForemInvalidos() {
        val criadorDeToolbarEAppBarConfiguration = spy(
            CriadorDeToolbarEAppBarConfiguration(
                criadorDeToolbar,
                criadorDeAppBarConfiguration
            )
        )
        doReturn(null).`when`(criadorDeToolbarEAppBarConfiguration)
            .buscaToolbar(any(AppBarLayout::class.java), anyInt())
        criadorDeToolbarEAppBarConfiguration.criar(0, 0, setOf(0))
    }
}