package br.com.eliascoelho911.notas.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.google.android.material.appbar.AppBarLayout
import java.lang.IllegalArgumentException

class CriadorDeToolbar(
    private val inflater: LayoutInflater,
    private val parent: ViewGroup
) {
    fun criar(@LayoutRes layout: Int): AppBarLayout {
        val appBarLayout = inflater.inflate(layout, parent, false)
        parent.addView(appBarLayout, 0)
        if (appBarLayout is AppBarLayout) {
            return appBarLayout
        } else {
            throw IllegalArgumentException("O layout precisa ter um AppBarLayout como root")
        }
    }
}
