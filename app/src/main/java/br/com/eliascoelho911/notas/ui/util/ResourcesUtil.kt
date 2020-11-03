package br.com.eliascoelho911.notas.ui.util

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import org.koin.java.KoinJavaComponent.inject

private val context: Context by inject(Context::class.java)

fun getDrawable(@DrawableRes drawable: Int) = ContextCompat.getDrawable(context, drawable)

@ColorInt
fun getColor(@ColorRes color: Int) = ContextCompat.getColor(context, color)