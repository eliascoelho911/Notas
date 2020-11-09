package br.com.eliascoelho911.notas.ui.databinding.adapter

import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("visivel")
fun FloatingActionButton.visivel(b: Boolean) {
    if (b) {
        show()
    } else {
        hide()
    }
}