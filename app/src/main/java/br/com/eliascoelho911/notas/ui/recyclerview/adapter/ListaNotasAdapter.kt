package br.com.eliascoelho911.notas.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.eliascoelho911.notas.databinding.ItemNotaBinding
import br.com.eliascoelho911.notas.model.NotaCompleta
import br.com.eliascoelho911.notas.ui.recyclerview.adapter.ListaNotasAdapter.NotaViewHolder
import org.koin.java.KoinJavaComponent.inject

open class ListaNotasAdapter(
    private var notas: List<NotaCompleta> = listOf(),
    private val onClickItem: (nota: NotaCompleta) -> Unit = {},
) : ListAdapter<NotaCompleta, NotaViewHolder>(DiffUtil()) {

    private val context: Context by inject(Context::class.java)
    private val atualizador = AtualizadorDeListaNotasAdapter(this)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemNotaBinding.inflate(inflater, parent, false)
        return NotaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        holder.vincula(notas[position])
    }

    fun atualiza(notasNovas: List<NotaCompleta>) {
        if (this.notas == notasNovas)
            return
        val notasAntigas = this.notas
        this.notas = notasNovas
        atualizador.atualiza(notasAntigas, notasNovas)
    }

    override fun getItemCount() = notas.size

    inner class NotaViewHolder(private val binding: ItemNotaBinding) : ViewHolder(binding.root) {
        fun vincula(nota: NotaCompleta) {
            binding.notaCompleta = nota
            binding.onClick = View.OnClickListener {
                onClickItem(nota)
            }
        }
    }

    private class DiffUtil : ItemCallback<NotaCompleta>() {
        override fun areItemsTheSame(oldItem: NotaCompleta, newItem: NotaCompleta) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: NotaCompleta, newItem: NotaCompleta) =
            oldItem == newItem
    }
}