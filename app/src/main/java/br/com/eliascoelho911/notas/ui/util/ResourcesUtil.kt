package br.com.eliascoelho911.notas.ui.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import org.koin.java.KoinJavaComponent.inject

private val context: Context by inject(Context::class.java)

fun getDrawable(@DrawableRes drawable: Int): Drawable? {
    return ContextCompat.getDrawable(context, drawable)
}