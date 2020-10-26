package br.com.eliascoelho911.notas.ui.main

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import br.com.eliascoelho911.notas.ui.CriadorDeAppBarConfiguration
import br.com.eliascoelho911.notas.ui.CriadorDeToolbar
import br.com.eliascoelho911.notas.ui.CriadorDeToolbarEAppBarConfiguration

class MainViewModel : ViewModel() {
    lateinit var navControlller: NavController
        private set
    lateinit var criadorDeToolbar: CriadorDeToolbar
        private set
    lateinit var criadorDeAppBarConfiguration: CriadorDeAppBarConfiguration
        private set
    private lateinit var aoAlterarAToolbar: (toolbar: Toolbar, appBarConfiguration: AppBarConfiguration?) -> Unit

    fun configura(
        navController: NavController,
        criadorDeToolbar: CriadorDeToolbar,
        criadorDeAppBarConfiguration: CriadorDeAppBarConfiguration,
        aoAlterarAToolbar: (toolbar: Toolbar, appBarConfiguration: AppBarConfiguration?) -> Unit
    ) {
        this.aoAlterarAToolbar = aoAlterarAToolbar
        this.criadorDeToolbar = criadorDeToolbar
        this.criadorDeAppBarConfiguration = criadorDeAppBarConfiguration
        this.navControlller = navController
    }

    private val criadorDeToolbarEAppBarConfiguration = CriadorDeToolbarEAppBarConfiguration(
        criadorDeToolbar,
        criadorDeAppBarConfiguration
    )

    fun toolbar(
        @LayoutRes layout: Int,
        @IdRes idToolbar: Int,
        topLevelDestinationIds: Set<Int>
    ) {
        val pair = criadorDeToolbarEAppBarConfiguration.criar(
            layout,
            idToolbar,
            topLevelDestinationIds
        )
        aoAlterarAToolbar(pair.first, pair.second)
    }
}