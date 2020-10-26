package br.com.eliascoelho911.notas.ui.main

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import br.com.eliascoelho911.notas.ui.CriadorDeAppBarConfiguration
import br.com.eliascoelho911.notas.ui.CriadorDeToolbar
import br.com.eliascoelho911.notas.ui.extension.procuraFilhoPorId

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

    fun toolbar(
        @LayoutRes layout: Int,
        @IdRes idToolbar: Int,
        topLevelDestinationIds: Set<Int>
    ) {
        val appBarLayout = criadorDeToolbar.criar(layout)
        val appBarConfiguration = criadorDeAppBarConfiguration.criar(topLevelDestinationIds)
        val toolbar = appBarLayout.procuraFilhoPorId(idToolbar) as Toolbar?
        toolbar?.run { aoAlterarAToolbar(this, appBarConfiguration) }
            ?: throw IllegalArgumentException("Erro ao encontrar a Toolbar. Verifique se a Toolbar é filha do AppBarLayout e se o parâmetro idToolbar está correto")
    }
}