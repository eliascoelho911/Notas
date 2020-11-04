package br.com.eliascoelho911.notas.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle.State.*
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.databinding.ItemCorBinding
import br.com.eliascoelho911.notas.ui.recyclerview.adapter.CoresAdapter.CorViewHolder
import br.com.eliascoelho911.notas.ui.util.getDrawable
import org.koin.java.KoinJavaComponent.inject

class CoresAdapter(
    private val cores: List<Int> = listOf(),
    val onClick: (cor: Int) -> Unit = {},
    val corDaNota: LiveData<Int>,
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

    override fun onViewAttachedToWindow(holder: CorViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.comecado()
    }

    override fun onViewDetachedFromWindow(holder: CorViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.finalizado()
    }

    inner class CorViewHolder(private val binding: ItemCorBinding) : ViewHolder(binding.root),
        LifecycleOwner {
        private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)
        private val foreground = ObservableField(getDrawable(R.drawable.background_circulo_item_cor_stroke))

        init {
            lifecycleRegistry.currentState = INITIALIZED
            corDaNota.observe(this, { corDaNotaAtualizado ->
                val cor = binding.cor
                cor?.let { corNaoNula ->
                    val novoForeground = if (corDaNotaAtualizado == corNaoNula) {
                        getDrawable(R.drawable.foreground_circulo_item_cor_selected)
                    } else {
                        getDrawable(R.drawable.background_circulo_item_cor_stroke)
                    }
                    foreground.set(novoForeground)
                }
            })
        }

        override fun getLifecycle() = lifecycleRegistry

        fun vincula(cor: Int) {
            binding.cor = cor
            binding.onClick = View.OnClickListener {
                this@CoresAdapter.onClick(cor)
            }
            binding.foreground = this.foreground
            binding.lifecycleOwner = this
        }

        fun comecado() {
            lifecycleRegistry.currentState = STARTED
        }

        fun finalizado() {
            lifecycleRegistry.currentState = DESTROYED
        }
    }

}