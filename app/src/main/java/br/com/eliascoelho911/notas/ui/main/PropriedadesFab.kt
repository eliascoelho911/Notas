package br.com.eliascoelho911.notas.ui.main

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PropriedadesFab {
    private val _visibilidade = MutableLiveData(true)
    val visibilidade: LiveData<Boolean> get() = _visibilidade

    private val _onClickListener = MutableLiveData(View.OnClickListener {})
    val onClickListener: LiveData<View.OnClickListener> get() = _onClickListener

    fun configura(
        visivel: Boolean,
        onClickListener: View.OnClickListener = View.OnClickListener {},
    ) {
        _visibilidade.value = visivel
        _onClickListener.value = onClickListener
    }
}