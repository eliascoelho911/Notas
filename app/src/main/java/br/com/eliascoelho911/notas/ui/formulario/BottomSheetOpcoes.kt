package br.com.eliascoelho911.notas.ui.formulario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.model.ItemMenu
import br.com.eliascoelho911.notas.ui.listview.adapter.ItemMenuAdapter
import br.com.eliascoelho911.notas.ui.recyclerview.adapter.CoresAdapter
import br.com.eliascoelho911.notas.ui.util.getColor
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BottomSheetOpcoes(
    private val notaData: NotaData,
) : BottomSheetDialogFragment() {
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

    private fun opcoes() = listOf(ItemMenu(getString(R.string.excluir), R.drawable.ic_delete),
        ItemMenu(getString(R.string.marcadores), R.drawable.ic_label))

    private fun cores() = listOf(getColor(R.color.azul_marinho),
        getColor(R.color.azul),
        getColor(R.color.vermelho),
        getColor(R.color.laranja),
        getColor(R.color.roxo),
        getColor(R.color.amarelo),
        getColor(R.color.ciano),
        getColor(R.color.verde))
}