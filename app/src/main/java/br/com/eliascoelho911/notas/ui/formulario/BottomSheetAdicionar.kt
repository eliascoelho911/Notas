package br.com.eliascoelho911.notas.ui.formulario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.model.ItemMenu
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetAdicionar : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val listView = inflater.inflate(R.layout.bottom_sheet_adicionar, container, false)
                as ListView
        val list = listOf(ItemMenu(getString(R.string.tirar_foto), R.drawable.ic_camera),
            ItemMenu(getString(R.string.adicionar_imagem), R.drawable.ic_image),
            ItemMenu(getString(R.string.caixas_de_selecao), R.drawable.ic_check_box))
        listView.adapter = BottomSheetAdicionarAdapter(list)
        return listView
    }
}