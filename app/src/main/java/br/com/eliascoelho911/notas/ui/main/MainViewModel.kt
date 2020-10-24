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
    private var bottomAppBar: BottomAppBar? = null

    fun configura(
        navController: NavController,
        coordinatorLayout: CoordinatorLayout,
        layoutInflater: LayoutInflater
    ) {
        this.navController = navController
        this.coordinatorLayout = coordinatorLayout
        this.layoutInflater = layoutInflater
    }

    fun bottomAppBar(@LayoutRes layout: Int?): BottomAppBar? {
        return if (layout == null) {
            semBottomAppBar()
            null
        } else {
            validaconfiguracaoDoViewModel()
            val bottomAppBarCriado = criaEExibeBottomAppBar(layout)
            NavigationUI.setupWithNavController(bottomAppBarCriado, navController)
            this.bottomAppBar = bottomAppBarCriado
            bottomAppBarCriado
        }
    }

    private fun criaEExibeBottomAppBar(layout: Int): BottomAppBar {
        val bottomAppBarCriado =
            layoutInflater.inflate(layout, coordinatorLayout, false) as BottomAppBar
        coordinatorLayout.addView(bottomAppBarCriado)
        return bottomAppBarCriado
    }

    private fun validaconfiguracaoDoViewModel() {
        if (!::coordinatorLayout.isInitialized || !::layoutInflater.isInitialized)
            throw MainViewModelNaoConfigurado()
    }

    private fun semBottomAppBar() {
        if (bottomAppBar != null) {
            coordinatorLayout.removeView(bottomAppBar)
            bottomAppBar = null
        }
    }
}