package br.com.eliascoelho911.notas.ui.main

import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ModificadorDeFab(private val fab: FloatingActionButton) {
    fun setOnClickListener(onClickListener: () -> Unit) {
        fab.setOnClickListener {
            onClickListener()
        }
    }

    fun setVisibility(isVisible: Boolean) {
        if (isVisible) {
            fab.show()
        } else {
            fab.hide()
        }
    }
}