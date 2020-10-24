package br.com.eliascoelho911.notas.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomappbar.BottomAppBar

open class CriadorDeBottomAppBar {
    fun criarExibirEConfigurarNavController(
        @LayoutRes layout: Int?,
        navController: NavController,
        coordinatorLayout: CoordinatorLayout,
        layoutInflater: LayoutInflater
    ): BottomAppBar? {
        return if (layout == null) {
            null
        } else {
            val bottomAppBarCriado = criarEExibir(
                layout,
                layoutInflater,
                coordinatorLayout
            )
            configuraNavController(bottomAppBarCriado, navController)
            bottomAppBarCriado
        }
    }

    open fun configuraNavController(
        bottomAppBar: BottomAppBar,
        navController: NavController
    ) {
        NavigationUI.setupWithNavController(bottomAppBar, navController)
    }

    open fun criarEExibir(
        layout: Int,
        layoutInflater: LayoutInflater,
        coordinatorLayout: ViewGroup
    ): BottomAppBar {
        val bottomAppBarCriado = criaBotaoAppBar(layoutInflater, layout, coordinatorLayout)
        coordinatorLayout.addView(bottomAppBarCriado)
        return bottomAppBarCriado
    }

    private fun criaBotaoAppBar(
        layoutInflater: LayoutInflater,
        layout: Int,
        coordinatorLayout: ViewGroup
    ): BottomAppBar {
        val bottomAppBarCriado =
            layoutInflater.inflate(layout, coordinatorLayout, false) as BottomAppBar
        bottomAppBarCriado.id = View.generateViewId()
        return bottomAppBarCriado
    }
}