package br.com.eliascoelho911.notas.ui.main

import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.LayoutParams.*

class ModificadorDeToolbar(private val toolbar: Toolbar) {
    private val scrollFlags: Int =
        SCROLL_FLAG_SCROLL or SCROLL_FLAG_ENTER_ALWAYS or SCROLL_FLAG_SNAP

    fun hideOnScroll(enable: Boolean) {
        val params = toolbar.layoutParams as AppBarLayout.LayoutParams
        if (enable) {
            toolbar.layoutParams = params.apply { scrollFlags = this@ModificadorDeToolbar.scrollFlags }
        } else {
            toolbar.layoutParams = params.apply { scrollFlags = 0 }
        }
    }
}