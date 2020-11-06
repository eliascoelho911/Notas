package br.com.eliascoelho911.notas.ui.databinding.adapter

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import com.google.android.material.appbar.AppBarLayout

@BindingAdapter("layout_scrollFlags")
fun Toolbar.layoutScrollFlags(scrollFlags: Int) {
    (layoutParams as AppBarLayout.LayoutParams).scrollFlags = scrollFlags
}