package br.com.eliascoelho911.notas.ui.extension

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.view.children

fun ViewGroup.procuraFilhoPorId(@IdRes id: Int): View? {
    children.iterator().forEach {
        if (it.id == id) {
            return it
        }
    }

    return null
}