package br.com.eliascoelho911.notas.model

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import br.com.eliascoelho911.notas.ui.util.getDrawable

data class ItemMenu(
    val texto: String,
    @DrawableRes val drawable: Int,
    val acaoAoClicar: View.OnClickListener = View.OnClickListener {},
) {
    val drawableObject: Drawable get() {
        return getDrawable(drawable)!!
    }
}