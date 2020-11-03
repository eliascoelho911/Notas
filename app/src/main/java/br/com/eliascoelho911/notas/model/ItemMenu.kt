package br.com.eliascoelho911.notas.model

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import br.com.eliascoelho911.notas.ui.util.getDrawable

data class ItemMenu(
    val texto: String,
    @DrawableRes val drawable: Int
) {
    val drawableObject: Drawable get() {
        return getDrawable(drawable)!!
    }
}