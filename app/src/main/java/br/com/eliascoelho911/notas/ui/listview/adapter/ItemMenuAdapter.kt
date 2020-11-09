package br.com.eliascoelho911.notas.ui.listview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.eliascoelho911.notas.databinding.ItemMenuBinding
import br.com.eliascoelho911.notas.ui.ItemMenu
import org.koin.java.KoinJavaComponent.inject

class ItemMenuAdapter(
    private val list: List<ItemMenu>,
) : BaseAdapter() {
    private val context: Context by inject(Context::class.java)

    override fun getCount() = list.size

    override fun getItem(p0: Int) = list[p0]

    override fun getItemId(p0: Int) = p0.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val binding = ItemMenuBinding.inflate(inflater, parent, false).apply {
            item = list[position]
        }
        return binding.root
    }
}