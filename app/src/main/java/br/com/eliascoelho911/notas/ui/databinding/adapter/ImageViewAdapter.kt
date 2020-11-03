package br.com.eliascoelho911.notas.ui.databinding.adapter

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter

@BindingAdapter("tint")
fun ImageView.tint(@ColorInt int: Int) {
    imageTintList = ColorStateList.valueOf(int)
}