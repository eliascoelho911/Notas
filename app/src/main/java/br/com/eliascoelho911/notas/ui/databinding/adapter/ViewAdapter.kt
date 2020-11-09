package br.com.eliascoelho911.notas.ui.databinding.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
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

@BindingAdapter("changeVisibilityWithFade")
fun View.changeVisibilityWithFade(visible: Boolean) {
    visibility = VISIBLE
    if (visible) {
        animate()
            .alpha(1f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    visibility = VISIBLE
                }
            })
            .setDuration(context.resources.getInteger(android.R.integer.config_shortAnimTime)
                .toLong())
            .start()
    } else {
        animate()
            .alpha(0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    visibility = GONE
                }
            })
            .setDuration(context.resources.getInteger(android.R.integer.config_shortAnimTime)
                .toLong())
            .start()
    }

}