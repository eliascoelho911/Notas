package br.com.eliascoelho911.notas.ui

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.AppBarConfiguration
import br.com.eliascoelho911.notas.ui.extension.procuraFilhoPorId
import com.google.android.material.appbar.AppBarLayout

open class CriadorDeToolbarEAppBarConfiguration(
    private val criadorDeToolbar: CriadorDeToolbar,
    private val criadorDeAppBarConfiguration: CriadorDeAppBarConfiguration
) {
    fun criar(
        @LayoutRes layout: Int,
        @IdRes idToolbar: Int,
        topLevelDestinationIds: Set<Int>
    ): Pair<Toolbar, AppBarConfiguration> {
        val appBarLayout = criadorDeToolbar.criarEExibir(layout)
        val appBarConfiguration = criadorDeAppBarConfiguration.criar(topLevelDestinationIds)
        val toolbar = buscaToolbar(appBarLayout, idToolbar)
            ?: throw IllegalArgumentException("Erro ao encontrar a Toolbar. Verifique se a Toolbar é filha do AppBarLayout e se o parâmetro idToolbar está correto")
        return Pair(toolbar, appBarConfiguration)
    }

    open fun buscaToolbar(
        appBarLayout: AppBarLayout?,
        idToolbar: Int
    ) = appBarLayout?.procuraFilhoPorId(idToolbar) as Toolbar?
}