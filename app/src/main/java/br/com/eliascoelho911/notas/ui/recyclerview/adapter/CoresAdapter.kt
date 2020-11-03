package br.com.eliascoelho911.notas.ui.recyclerview.adapter

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.eliascoelho911.notas.databinding.ItemCorBinding
import br.com.eliascoelho911.notas.ui.recyclerview.adapter.CoresAdapter.CorViewHolder
import org.koin.java.KoinJavaComponent.inject

class CoresAdapter(
    private val cores: List<Int>
) : Adapter<CorViewHolder>() {
    private val context: Context by inject(Context::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CorViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemCorBinding.inflate(inflater, parent, false)
        return CorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CorViewHolder, position: Int) {
        holder.vincula(cores[position])
    }

    override fun getItemCount() = cores.size

    class CorViewHolder(private val binding: ItemCorBinding) : ViewHolder(binding.root) {
        fun vincula(int: Int) {
            binding.cor = int
        }
    }
}