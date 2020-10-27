package br.com.eliascoelho911.notas.ui.main

import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import br.com.eliascoelho911.notas.ui.CriadorDeToolbarEAppBarConfiguration

class MainViewModel : ViewModel() {
    lateinit var navControlller: NavController
        private set
    private lateinit var aoAlterarAToolbar: (toolbar: Toolbar, appBarConfiguration: AppBarConfiguration?) -> Unit
    private lateinit var criadorDeToolbarEAppBarConfiguration: CriadorDeToolbarEAppBarConfiguration

    @LayoutRes
    private var atualLayoutDeToolbar: Int? = null

    fun configura(
        navController: NavController,
        criadorDeToolbarEAppBarConfiguration: CriadorDeToolbarEAppBarConfiguration,
        aoAlterarAToolbar: (toolbar: Toolbar, appBarConfiguration: AppBarConfiguration?) -> Unit,
    ) {
        this.aoAlterarAToolbar = aoAlterarAToolbar
        this.criadorDeToolbarEAppBarConfiguration = criadorDeToolbarEAppBarConfiguration
        this.navControlller = navController
    }

    fun toolbar(
        @LayoutRes layout: Int,
        topLevelDestinationIds: Set<Int>? = null,
    ) {
        if (layout == atualLayoutDeToolbar)
            return
        val toolbar = criadorDeToolbarEAppBarConfiguration.criarToolbar(layout)
        val appBarConfiguration = topLevelDestinationIds?.run {
            criadorDeToolbarEAppBarConfiguration.criarAppBarConfiguration(this)
        }
        aoAlterarAToolbar(toolbar, appBarConfiguration)
        atualLayoutDeToolbar = layout
    }
}