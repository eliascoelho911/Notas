package br.com.eliascoelho911.notas.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.appbar.AppBarLayout.LayoutParams.*

class PropriedadesToolbar {
    private val defaultScrollFlags =
        SCROLL_FLAG_SCROLL or SCROLL_FLAG_ENTER_ALWAYS or SCROLL_FLAG_SNAP

    private val _scrollFlags = MutableLiveData(defaultScrollFlags)
    val scrollFlags: LiveData<Int> get() = _scrollFlags

    fun configura(escondeAoMoverScroll: Boolean, toolbarEditText: ToolbarEditText? = null) {
        _scrollFlags.value = if (escondeAoMoverScroll) {
            defaultScrollFlags
        } else {
            SCROLL_FLAG_NO_SCROLL
        }
        _toolbarEditText.value = toolbarEditText
    }

    private val _toolbarEditText: MutableLiveData<ToolbarEditText?> = MutableLiveData(null)
    val toolbarEditText: LiveData<ToolbarEditText?> get() = _toolbarEditText

    class ToolbarEditText(val hint: String)
}