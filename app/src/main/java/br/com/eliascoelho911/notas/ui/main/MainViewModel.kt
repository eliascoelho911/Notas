package br.com.eliascoelho911.notas.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    lateinit var modificadorDeFab: ModificadorDeFab
    lateinit var modificadorDeBottomAppBar: ModificadorDeBottomAppBar
    lateinit var modificadorDeToolbar: ModificadorDeToolbar

    fun configurarLayout(fabVisivel: Boolean, bottomAppBarVisivel: Boolean, hideOnScroll: Boolean) {
        modificadorDeFab.setVisibility(fabVisivel)
        modificadorDeBottomAppBar.setVisibility(bottomAppBarVisivel)
        modificadorDeToolbar.hideOnScroll(hideOnScroll)
    }
}
