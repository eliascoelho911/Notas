package br.com.eliascoelho911.notas.ui.formulario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.model.ItemMenu
import br.com.eliascoelho911.notas.ui.listview.adapter.ItemMenuAdapter
import br.com.eliascoelho911.notas.ui.recyclerview.adapter.CoresAdapter
import br.com.eliascoelho911.notas.ui.util.getColor
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

open class BottomSheetOpcoes(
    private val notaData: NotaData,
) : BottomSheetDialogFragment() {
    private val formularioViewModel: FormularioViewModel by sharedViewModel()
    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_formulario_opcoes, container, false)
        return view.apply {
            findViewById<ListView>(R.id.bottom_sheet_formulario_opcoes_lista).adapter =
                ItemMenuAdapter(opcoes())
            findViewById<RecyclerView>(R.id.bottom_sheet_formulario_opcoes_cores).adapter =
                CoresAdapter(cores(), corDaNota = notaData.cor, onClick = { cor ->
                    notaData.cor.value = cor
                    this@BottomSheetOpcoes.dismiss()
                })
        }
    }

    private fun opcoes() =
        listOf(ItemMenu(getString(R.string.excluir), R.drawable.ic_delete) {
            formularioViewModel.deleta(notaData.paraNotaCompleta().nota)
            dismiss()
            navController.popBackStack()
        }, ItemMenu(getString(R.string.marcadores), R.drawable.ic_label) {
            dismiss()
            val navFormularioParaNavMarcadores =
                FormularioFragmentDirections.navFormularioParaNavMarcadores(notaData.paraNotaCompleta())
            navController.navigate(navFormularioParaNavMarcadores)
        })

    private fun cores() = listOf(getColor(R.color.background_tela),
        getColor(R.color.azul),
        getColor(R.color.vermelho),
        getColor(R.color.laranja),
        getColor(R.color.roxo),
        getColor(R.color.amarelo),
        getColor(R.color.azul_marinho),
        getColor(R.color.verde))
}