package br.com.eliascoelho911.notas.ui.main

import android.view.View
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    fun configuracaoPadrao() {
        propriedadesToolbar.configura(escondeAoMoverScroll = false)
        propriedadesBottomAppBar.alteraVisibilidade(View.GONE)
        propriedadesFab.configura(visivel = View.GONE)
    }

    val propriedadesToolbar = PropriedadesToolbar()
    val propriedadesBottomAppBar = PropriedadesBottomAppBar()
    val propriedadesFab = PropriedadesFab()
}


