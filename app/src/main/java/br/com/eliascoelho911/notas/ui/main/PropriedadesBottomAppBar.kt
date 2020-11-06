package br.com.eliascoelho911.notas.ui.main

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PropriedadesBottomAppBar {
    private val _visibilidade = MutableLiveData(View.VISIBLE)
    val visibilidade: LiveData<Int> get() = _visibilidade

    fun alteraVisibilidade(visivel: Int) {
        _visibilidade.value = visivel
    }
}