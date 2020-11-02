package br.com.eliascoelho911.notas.ui.main

import android.view.View
import com.google.android.material.bottomappbar.BottomAppBar

class ModificadorDeBottomAppBar(private val bottomAppBar: BottomAppBar) {
    fun setVisibility(isVisible: Boolean) {
        if (isVisible) {
            bottomAppBar.visibility = View.VISIBLE
        } else {
            bottomAppBar.visibility = View.GONE
        }
    }
}