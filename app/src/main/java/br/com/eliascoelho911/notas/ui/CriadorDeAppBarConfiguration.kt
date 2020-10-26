package br.com.eliascoelho911.notas.ui

import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration

open class CriadorDeAppBarConfiguration(
    private val drawerLayout: DrawerLayout
) {
    fun criar(topLevelDestinationsIds: Set<Int>) = AppBarConfiguration(topLevelDestinationsIds, drawerLayout)
}