package br.com.eliascoelho911.notas.ui

import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.AppBarConfiguration
import br.com.eliascoelho911.notas.ui.extension.procuraFilhoPorTipo
import com.google.android.material.appbar.AppBarLayout

open class CriadorDeToolbar(
    private val criadorDeAppBarLayout: CriadorDeAppBarLayout
) {
    fun criarToolbar(
        @LayoutRes layout: Int
    ): Toolbar {
        val appBarLayout = criadorDeAppBarLayout.criarEExibir(layout)
        return buscaToolbar(appBarLayout)
    }

    open fun buscaToolbar(
        appBarLayout: AppBarLayout
    ): Toolbar {
        val todasToolbar: List<Toolbar> = appBarLayout.procuraFilhoPorTipo()
        return todasToolbar.first()
    }
}