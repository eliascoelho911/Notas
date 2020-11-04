package br.com.eliascoelho911.notas.ui.databinding.adapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("backgroundColor")
fun View.backgroundColor(int: Int) {
    setBackgroundColor(int)
}