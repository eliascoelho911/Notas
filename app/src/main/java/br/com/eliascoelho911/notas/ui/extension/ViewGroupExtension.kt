package br.com.eliascoelho911.notas.ui.extension

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.IdRes
import androidx.core.view.children
import kotlin.reflect.KClass

inline fun <reified T : View> ViewGroup.procuraFilhoPorTipo(): List<T> {
    val list = mutableListOf<T>()
    children.iterator().forEach {
        if (it is T) {
            list.add(it)
        }
    }
    return list
}