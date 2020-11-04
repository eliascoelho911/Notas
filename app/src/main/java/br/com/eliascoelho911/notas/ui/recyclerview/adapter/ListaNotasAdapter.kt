package br.com.eliascoelho911.notas.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.eliascoelho911.notas.databinding.ItemNotaBinding
import br.com.eliascoelho911.notas.model.Nota
import br.com.eliascoelho911.notas.ui.recyclerview.adapter.ListaNotasAdapter.NotaViewHolder
import org.koin.java.KoinJavaComponent.inject

class ListaNotasAdapter(private var notas: List<Nota> = listOf()) :
    ListAdapter<Nota, NotaViewHolder>(DiffUtil) {

    private val context: Context by inject(Context::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemNotaBinding.inflate(inflater, parent, false)
        return NotaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        holder.vincula(notas[position])
    }

    fun atualiza(notas: List<Nota>) {
        this.notas = notas
        notifyDataSetChanged()
    }

    override fun getItemCount() = notas.size

    inner class NotaViewHolder(private val binding: ItemNotaBinding) : ViewHolder(binding.root) {
        fun vincula(nota: Nota) {
            binding.nota = nota
        }
    }

    private object DiffUtil : ItemCallback<Nota>() {
        override fun areItemsTheSame(oldItem: Nota, newItem: Nota) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Nota, newItem: Nota) = oldItem == newItem
    }
}