package br.com.eliascoelho911.notas.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.eliascoelho911.notas.databinding.ItemMarcadorBinding
import br.com.eliascoelho911.notas.model.Marcador
import br.com.eliascoelho911.notas.ui.recyclerview.adapter.MarcadoresAdapter.MarcadorViewHolder
import org.koin.java.KoinJavaComponent.inject

class MarcadoresAdapter(
    private val marcadores: List<Marcador> = listOf(),
    private val onClick: (Marcador) -> Unit = {},
) : ListAdapter<Marcador, MarcadorViewHolder>(DiffUtil()) {
    private val context by inject(Context::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarcadorViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemMarcadorBinding.inflate(inflater, parent, false)
        return MarcadorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarcadorViewHolder, position: Int) {
        holder.vincula(marcadores[position])
    }

    override fun getItemCount() = marcadores.size

    inner class MarcadorViewHolder(private val binding: ItemMarcadorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun vincula(marcador: Marcador) {
            binding.marcador = marcador
            binding.onClick = View.OnClickListener { onClick(marcador) }
        }
    }

    private class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Marcador>() {
        override fun areItemsTheSame(oldItem: Marcador, newItem: Marcador) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Marcador, newItem: Marcador) = oldItem == newItem
    }

}