package br.com.eliascoelho911.notas.ui.main

import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import br.com.eliascoelho911.notas.exception.MainViewModelNaoConfigurado
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainViewModel : ViewModel() {
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var layoutInflater: LayoutInflater
    private lateinit var navController: NavController
    private var bottomAppBar: BottomAppBar? = null
    private var fab: FloatingActionButton? = null

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
            validaConfiguracaoDoViewModel()
            val bottomAppBarCriado = criaEExibeBottomAppBar(layout)
            NavigationUI.setupWithNavController(bottomAppBarCriado, navController)
            this.bottomAppBar = bottomAppBarCriado
            bottomAppBarCriado
        }
    }

    fun fab(@LayoutRes layout: Int?, anchorBottomAppBar: Boolean): FloatingActionButton? {
        return if (layout == null) {
            semFab()
            null
        } else {
            validaConfiguracaoDoViewModel()
            val fabCriado = criaEExibeFab(layout, anchorBottomAppBar)
            this.fab = fabCriado
            fabCriado
        }
    }

    private fun semFab() {
        if (fab != null) {
            coordinatorLayout.removeView(fab)
            fab = null
        }
    }

    private fun criaEExibeFab(layout: Int, anchorBottomAppBar: Boolean): FloatingActionButton {
        val fab = layoutInflater.inflate(layout, coordinatorLayout, false)
                as FloatingActionButton
        fab.id = View.generateViewId()
        if (anchorBottomAppBar) {
            bottomAppBar?.let {
                val layoutParams = fab.layoutParams as CoordinatorLayout.LayoutParams
                layoutParams.anchorId = it.id
                fab.layoutParams = layoutParams
            }
        }
        coordinatorLayout.addView(fab)
        return fab
    }

    private fun criaEExibeBottomAppBar(layout: Int): BottomAppBar {
        val bottomAppBarCriado =
            layoutInflater.inflate(layout, coordinatorLayout, false) as BottomAppBar
        bottomAppBarCriado.id = View.generateViewId()
        coordinatorLayout.addView(bottomAppBarCriado)
        return bottomAppBarCriado
    }

    private fun validaConfiguracaoDoViewModel() {
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