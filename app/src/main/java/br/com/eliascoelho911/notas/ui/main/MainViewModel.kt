package br.com.eliascoelho911.notas.ui.main

import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import br.com.eliascoelho911.notas.exception.MainViewModelNaoConfigurado
import com.google.android.material.bottomappbar.BottomAppBar

class MainViewModel : ViewModel() {
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var layoutInflater: LayoutInflater
    private lateinit var navController: NavController

    fun configura(
        navController: NavController,
        coordinatorLayout: CoordinatorLayout,
        layoutInflater: LayoutInflater
    ) {
        this.navController = navController
        this.coordinatorLayout = coordinatorLayout
        this.layoutInflater = layoutInflater
    }

    fun bottomAppBar(@LayoutRes layout: Int): BottomAppBar {
        if (!::coordinatorLayout.isInitialized || !::layoutInflater.isInitialized)
            throw MainViewModelNaoConfigurado()
        val bottomAppBar = layoutInflater.inflate(layout, coordinatorLayout, false) as BottomAppBar
        coordinatorLayout.addView(bottomAppBar)
        NavigationUI.setupWithNavController(bottomAppBar, navController)
        return bottomAppBar
    }
}