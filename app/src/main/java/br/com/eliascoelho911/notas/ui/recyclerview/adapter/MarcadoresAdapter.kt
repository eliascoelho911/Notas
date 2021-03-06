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
    val marcadorSelecionado: Marcador?,
    var onClick: (Marcador) -> Unit = {},
) : ListAdapter<Marcador, MarcadorViewHolder>(DiffUtil()) {
    private val context by inject(Context::class.java)
    private lateinit var listaSemFiltro: List<Marcador>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarcadorViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemMarcadorBinding.inflate(inflater, parent, false)
        return MarcadorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarcadorViewHolder, position: Int) {
        holder.vincula(getItem(position))
    }

    fun atualiza(list: List<Marcador>) {
        listaSemFiltro = list
        submitList(list)
    }

    fun filtra(s: String) {
        if (::listaSemFiltro.isInitialized)
            submitList(listaSemFiltro.filter { it.nome.contains(s, true) })
    }

    fun limpaFiltro() {
        if (::listaSemFiltro.isInitialized)
            submitList(listaSemFiltro)
    }

    inner class MarcadorViewHolder(private val binding: ItemMarcadorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun vincula(marcador: Marcador) {
            binding.marcador = marcador
            binding.onClick = View.OnClickListener { onClick(marcador) }
            binding.isSelected = marcadorSelecionado?.run { marcador.id == id } ?: false
        }
    }

    private class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Marcador>() {
        override fun areItemsTheSame(oldItem: Marcador, newItem: Marcador) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Marcador, newItem: Marcador) = oldItem == newItem
    }

}