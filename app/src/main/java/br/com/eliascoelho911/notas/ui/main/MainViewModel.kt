package br.com.eliascoelho911.notas.ui.main

import android.view.View
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    fun configuracaoPadrao() {
        propriedadesToolbar.esconderAoMoverScroll(b = false)
        propriedadesToolbar.toolbarEditText.altera()
        propriedadesBottomAppBar.alteraVisibilidade(View.GONE)
        propriedadesFab.configura(visivel = false)
    }

    val propriedadesToolbar = PropriedadesToolbar()
    val propriedadesBottomAppBar = PropriedadesBottomAppBar()
    val propriedadesFab = PropriedadesFab()
}


