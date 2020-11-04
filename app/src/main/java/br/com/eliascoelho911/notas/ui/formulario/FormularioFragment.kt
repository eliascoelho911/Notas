package br.com.eliascoelho911.notas.ui.formulario

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import br.com.eliascoelho911.notas.databinding.FragmentFormularioBinding
import br.com.eliascoelho911.notas.model.Nota
import br.com.eliascoelho911.notas.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_formulario.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

open class FormularioFragment : Fragment() {
    private val viewModel: FormularioViewModel by viewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()
    private val navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        configuraMainViewModel()
        criaNotaData()
        return getBinding(container).root
    }

    private fun exibeBottomSheetAdicionar() {
        BottomSheetAdicionar().show(requireActivity().supportFragmentManager, null)
    }

    private fun getBinding(container: ViewGroup?): FragmentFormularioBinding {
        return FragmentFormularioBinding.inflate(layoutInflater, container, false).apply {
            nota = viewModel.notaData
            aoClicarNoBotaoAdicionar = View.OnClickListener {
                exibeBottomSheetAdicionar()
            }
            aoClicarNoBotaoOpcoes = View.OnClickListener {
                exibeBottomSheetOpcoes()
            }
            lifecycleOwner = this@FormularioFragment.viewLifecycleOwner
        }
    }

    private fun exibeBottomSheetOpcoes() {
        BottomSheetOpcoes(viewModel.notaData).show(requireActivity().supportFragmentManager, null)
    }

    private fun configuraMainViewModel() {
        mainViewModel.configurarLayout(fabVisivel = false,
            bottomAppBarVisivel = false,
            hideOnScroll = false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraAcaoDoBotaoVoltar()
        corrigeVisibilidadeDasViews()
    }

    private fun corrigeVisibilidadeDasViews() {
        @Suppress("COMPATIBILITY_WARNING")
        viewModel.notaData.descricao.observe(viewLifecycleOwner, {
            fragment_formulario_descricao_texto.visibility = it?.run { VISIBLE } ?: GONE
            fragment_formulario_descricao_tasklist.visibility = it?.run { GONE } ?: VISIBLE
        })
    }

    private fun criaNotaData() {
        viewModel.notaData = NotaData(Nota())
    }

    private fun configuraAcaoDoBotaoVoltar() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            salvaNotaSeAlgumCampoEstiverPreenchido(viewModel)
            escondeTeclado()
            navController.popBackStack()
        }
    }

    fun salvaNotaSeAlgumCampoEstiverPreenchido(viewModel: FormularioViewModel) {
        if (algumCampoEstiverPreenchido()) {
            salvaNota(criaNota(), viewModel)
        }
    }

    private fun escondeTeclado() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(fragment_formulario_titulo.windowToken, 0)
        imm.hideSoftInputFromWindow(fragment_formulario_descricao_texto.windowToken, 0)
    }

    open fun algumCampoEstiverPreenchido(): Boolean {
        return fragment_formulario_titulo.text.isNotEmpty() || fragment_formulario_descricao_texto.text.isNotEmpty()
    }

    open fun salvaNota(nota: Nota, viewModel: FormularioViewModel) {
        viewModel.salvar(nota)
    }

    open fun criaNota(): Nota {
        return viewModel.notaData.paraNota()
    }

}