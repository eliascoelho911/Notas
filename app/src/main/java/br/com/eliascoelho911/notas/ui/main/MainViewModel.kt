package br.com.eliascoelho911.notas.ui.main

import android.util.Log
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import br.com.eliascoelho911.notas.ui.CriadorDeAppBarConfiguration
import br.com.eliascoelho911.notas.ui.CriadorDeToolbar

class MainViewModel : ViewModel() {
    lateinit var navController: NavController
        private set
    private lateinit var aoAlterarAToolbar: (toolbar: Toolbar, appBarConfiguration: AppBarConfiguration) -> Unit
    private lateinit var criadorDeToolbar: CriadorDeToolbar
    private lateinit var criadorDeAppBarConfiguration: CriadorDeAppBarConfiguration
    lateinit var appBarConfiguration: AppBarConfiguration
        private set

    @LayoutRes
    private var atualLayoutDeToolbar: Int? = null

    fun configura(
        navController: NavController,
        criadorDeToolbar: CriadorDeToolbar,
        criadorDeAppBarConfiguration: CriadorDeAppBarConfiguration,
        aoAlterarAToolbar: (toolbar: Toolbar, appBarConfiguration: AppBarConfiguration) -> Unit,
    ) {
        this.aoAlterarAToolbar = aoAlterarAToolbar
        this.criadorDeToolbar = criadorDeToolbar
        this.criadorDeAppBarConfiguration = criadorDeAppBarConfiguration
        this.navController = navController
    }

    fun toolbar(
        @LayoutRes layout: Int,
        topLevelDestinationIds: Set<Int>? = null,
    ) {
        if (layout == atualLayoutDeToolbar)
            return
        val toolbar = criadorDeToolbar.criarToolbar(layout)
        appBarConfiguration = criaAppBarConfiguration(topLevelDestinationIds)
        aoAlterarAToolbar(toolbar, appBarConfiguration)
        atualLayoutDeToolbar = layout
    }

    private fun criaAppBarConfiguration(topLevelDestinationIds: Set<Int>?) =
        (topLevelDestinationIds?.run { criadorDeAppBarConfiguration.criar(topLevelDestinationIds) }
            ?: AppBarConfiguration(navController.graph))
}
