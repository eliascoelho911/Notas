package br.com.eliascoelho911.notas.ui.main

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.appbar.AppBarLayout.LayoutParams.*

class PropriedadesToolbar {
    private val defaultScrollFlags =
        SCROLL_FLAG_SCROLL or SCROLL_FLAG_ENTER_ALWAYS or SCROLL_FLAG_SNAP

    private val _scrollFlags = MutableLiveData(defaultScrollFlags)
    val scrollFlags: LiveData<Int> get() = _scrollFlags

    fun esconderAoMoverScroll(b: Boolean) {
        _scrollFlags.value = if (b) {
            defaultScrollFlags
        } else {
            SCROLL_FLAG_NO_SCROLL
        }
    }

    val toolbarEditText = ToolbarEditText()

    class ToolbarEditText {
        private val _hint = MutableLiveData("")
        val hint: LiveData<String> get() = _hint

        private val _visibilidade = MutableLiveData(GONE)
        val visibilidade: LiveData<Int> get() = _visibilidade

        val text = MutableLiveData("")

        fun altera(hint: String? = null) {
            _hint.value = hint
            _visibilidade.value = hint?.run { VISIBLE } ?: GONE
        }

        fun limpaTexto() {
            text.value = ""
        }
    }
}