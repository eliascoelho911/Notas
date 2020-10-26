package br.com.eliascoelho911.notas.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.google.android.material.appbar.AppBarLayout

open class CriadorDeToolbar(
    private val inflater: LayoutInflater,
    private val parent: ViewGroup
) {
    open fun criarEExibir(@LayoutRes layout: Int): AppBarLayout {
        val appBarLayout = inflaLayout(layout)
        if (appBarLayout is AppBarLayout) {
            exibe(appBarLayout)
            return appBarLayout
        } else {
            throw IllegalArgumentException("O layout precisa ter um AppBarLayout como root")
        }
    }

    open fun exibe(appBarLayout: AppBarLayout) {
        parent.addView(appBarLayout, 0)
    }

    open fun inflaLayout(layout: Int): View = inflater.inflate(layout, parent, false)
}
