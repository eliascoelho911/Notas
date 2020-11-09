package br.com.eliascoelho911.notas.ui.formulario

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.core.content.ContextCompat
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.ui.ItemMenu
import br.com.eliascoelho911.notas.ui.listview.adapter.ItemMenuAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BottomSheetAdicionar : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return ListView(requireContext()).apply {
            dividerHeight = 0
            background =
                ColorDrawable(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
            adapter = ItemMenuAdapter(opcoes())
        }
    }

    private fun opcoes(): List<ItemMenu> {
        return listOf(ItemMenu(getString(R.string.tirar_foto), R.drawable.ic_camera),
            ItemMenu(getString(R.string.adicionar_imagem), R.drawable.ic_image),
            ItemMenu(getString(R.string.caixas_de_selecao), R.drawable.ic_check_box))
    }
}