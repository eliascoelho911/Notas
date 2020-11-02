package br.com.eliascoelho911.notas.ui.main

import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout

class ModificadorDeToolbar(private val toolbar: Toolbar) {
    private val scrollFlags: Int = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
            AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS

    fun hideOnScroll(enable: Boolean) {
        val params = toolbar.layoutParams as AppBarLayout.LayoutParams
        if (enable) {
            toolbar.layoutParams = params.apply { scrollFlags = this@ModificadorDeToolbar.scrollFlags }
        } else {
            toolbar.layoutParams = params.apply { scrollFlags = 0 }
        }
    }
}