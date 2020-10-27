package br.com.eliascoelho911.notas.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import br.com.eliascoelho911.notas.exception.AppBarLayoutComMaisDeUmaToolbar
import br.com.eliascoelho911.notas.exception.AppBarLayoutSemToolbar
import br.com.eliascoelho911.notas.exception.ViewRootNaoEhAppBarLayout
import br.com.eliascoelho911.notas.ui.extension.procuraFilhoPorTipo
import com.google.android.material.appbar.AppBarLayout

open class CriadorDeAppBarLayout(
    private val inflater: LayoutInflater,
    private val parent: ViewGroup
) {
    open fun criarEExibir(@LayoutRes layout: Int): AppBarLayout {
        val viewCriada = inflaLayout(layout)
        valida(viewCriada)
        substituiAtualAppBarLayoutPor(viewCriada)
        return viewCriada as AppBarLayout
    }

    open fun valida(viewCriada: View) {
        if (viewCriada !is AppBarLayout)
            throw ViewRootNaoEhAppBarLayout()

        val procuraTodasToolbar: List<Toolbar> = viewCriada.procuraFilhoPorTipo()
        if (procuraTodasToolbar.isEmpty())
            throw AppBarLayoutSemToolbar()
        if (procuraTodasToolbar.size > 1)
            throw AppBarLayoutComMaisDeUmaToolbar()
    }

    open fun substituiAtualAppBarLayoutPor(view: View) {
        removeAppBarLayoutJaExistente()
        parent.addView(view, 0)
    }

    private fun removeAppBarLayoutJaExistente() {
        val appBarLayoutJaExibido: List<AppBarLayout> = parent.procuraFilhoPorTipo()
        appBarLayoutJaExibido.forEach { parent.removeView(it) }
    }

    open fun inflaLayout(layout: Int): View = inflater.inflate(layout, parent, false)
}
