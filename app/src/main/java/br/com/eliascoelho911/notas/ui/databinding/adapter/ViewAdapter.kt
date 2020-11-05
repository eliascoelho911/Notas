package br.com.eliascoelho911.notas.ui.databinding.adapter

import android.view.View
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter

@BindingAdapter("backgroundColor")
fun View.backgroundColor(int: Int) {
    setBackgroundColor(int)
}

@BindingAdapter("layout_height")
fun View.setLayoutHeight(height: Int) {
    val novoLayoutParams: LinearLayout.LayoutParams = layoutParams as LinearLayout.LayoutParams
    novoLayoutParams.height = height
    layoutParams = novoLayoutParams
}