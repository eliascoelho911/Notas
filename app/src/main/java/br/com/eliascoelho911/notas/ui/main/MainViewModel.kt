package br.com.eliascoelho911.notas.ui.main

import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import br.com.eliascoelho911.notas.exception.MainViewModelNaoConfigurado
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.java.KoinJavaComponent.inject

class MainViewModel : ViewModel() {
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var layoutInflater: LayoutInflater
    private lateinit var navController: NavController
    private var bottomAppBar: BottomAppBar? = null
    private var fab: FloatingActionButton? = null
    private val criadorDeBottomAppBar: CriadorDeBottomAppBar by inject(CriadorDeBottomAppBar::class.java)

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
            criaBottomAppBar(layout)
        }
    }

    private fun criaBottomAppBar(layout: Int?): BottomAppBar? {
        bottomAppBar = criadorDeBottomAppBar.criarExibirEConfigurarNavController(layout, navController, coordinatorLayout, layoutInflater)
        return bottomAppBar
    }

    private fun semBottomAppBar() {
        if (bottomAppBar != null) {
            coordinatorLayout.removeView(bottomAppBar)
            bottomAppBar = null
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

    private fun validaConfiguracaoDoViewModel() {
        if (!::coordinatorLayout.isInitialized || !::layoutInflater.isInitialized)
            throw MainViewModelNaoConfigurado()
    }

}