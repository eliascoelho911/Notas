package br.com.eliascoelho911.notas.ui.formulario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FormularioFragment : Fragment() {
    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        exibeToolbar()
        return inflater.inflate(R.layout.fragment_formulario, container, false)
    }

    private fun exibeToolbar() {
        mainViewModel.toolbar(R.layout.toolbar, null)
    }
}