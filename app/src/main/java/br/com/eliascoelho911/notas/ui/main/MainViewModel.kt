package br.com.eliascoelho911.notas.ui.main

import android.view.LayoutInflater
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class MainViewModel : ViewModel() {
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var layoutInflater: LayoutInflater
    private lateinit var _navController: NavController
    val navController get() = _navController

    fun configura(
        navController: NavController,
        coordinatorLayout: CoordinatorLayout,
        layoutInflater: LayoutInflater
    ) {
        this._navController = navController
        this.coordinatorLayout = coordinatorLayout
        this.layoutInflater = layoutInflater
    }
}