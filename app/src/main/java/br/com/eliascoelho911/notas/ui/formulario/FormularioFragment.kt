package br.com.eliascoelho911.notas.ui.formulario

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.model.Nota
import br.com.eliascoelho911.notas.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_formulario.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

open class FormularioFragment : Fragment() {
    private val mainViewModel: MainViewModel by sharedViewModel()
    private val viewModel: FormularioViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        exibeToolbar()
        return inflater.inflate(R.layout.fragment_formulario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraAcaoDoBotaoVoltar()
    }

    private fun configuraAcaoDoBotaoVoltar() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            salvaNotaSeAlgumCampoEstiverPreenchido()
            escondeTeclado()
            mainViewModel.navController.popBackStack()
        }
    }

    fun salvaNotaSeAlgumCampoEstiverPreenchido() {
        if (algumCampoEstiverPreenchido()) {
            salvaNota(criaNota())
        }
    }

    private fun escondeTeclado() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(fragment_formulario_titulo.windowToken, 0)
        imm.hideSoftInputFromWindow(fragment_formulario_nota.windowToken, 0)
    }

    open fun algumCampoEstiverPreenchido(): Boolean {
        return fragment_formulario_titulo.text.isNotEmpty() || fragment_formulario_nota.text.isNotEmpty()
    }

    open fun salvaNota(nota: Nota) {
        viewModel.salvar(nota)
    }

    open fun criaNota(): Nota {
        val titulo = fragment_formulario_titulo.text.toString()
        val descricao = fragment_formulario_nota.text.toString()
        return Nota(titulo = titulo, descricao = descricao)
    }

    private fun exibeToolbar() {
        mainViewModel.toolbar(R.layout.toolbar)
    }
}