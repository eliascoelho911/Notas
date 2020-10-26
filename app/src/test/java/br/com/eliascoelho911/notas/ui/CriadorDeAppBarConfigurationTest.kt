package br.com.eliascoelho911.notas.ui

import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CriadorDeAppBarConfigurationTest {
    @Mock
    private lateinit var drawerLayout: DrawerLayout
    @Test
    fun deve_RetornarUmAppBarConfiguration_QuandoCriar() {
        val criador = CriadorDeAppBarConfiguration(drawerLayout)
        val topLevelDestinationsIds = setOf(1)
        val appBarConfiguration = AppBarConfiguration(topLevelDestinationsIds, drawerLayout)
        val appBarConfigurationCriado = criador.criar(topLevelDestinationsIds)
        assertEquals(appBarConfiguration.openableLayout, appBarConfigurationCriado.openableLayout)
        assertEquals(appBarConfiguration.topLevelDestinations, appBarConfigurationCriado.topLevelDestinations)
    }
}