package br.com.eliascoelho911.notas.ui.main

import android.view.LayoutInflater
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.NavController
import com.google.android.material.bottomappbar.BottomAppBar
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CriadorDeBottomAppBarTest {

    @Mock
    private lateinit var navController: NavController

    @Mock
    private lateinit var coordinatorLayout: CoordinatorLayout

    @Mock
    private lateinit var layoutInflater: LayoutInflater

    @Mock
    private lateinit var bottomAppBar: BottomAppBar

    @Spy
    private val criador = CriadorDeBottomAppBar()

    @Test
    fun deve_RetornarNulo_QuandoOLayoutForNulo() {
        val criado = criador.criarExibirEConfigurarNavController(null, navController, coordinatorLayout, layoutInflater)
        assertNull(criado)
    }

    @Test
    fun deve_CriarBottomAppBar_QuandoOLayoutNaoForNulo() {
        doReturn(bottomAppBar).`when`(criador).criarEExibir(0, layoutInflater, coordinatorLayout)
        doNothing().`when`(criador).configuraNavController(bottomAppBar, navController)
        val botaoCriado = criador.criarExibirEConfigurarNavController(0, navController, coordinatorLayout, layoutInflater)
        assertEquals(botaoCriado, bottomAppBar)
    }
}