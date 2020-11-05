package br.com.eliascoelho911.notas.ui.formulario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.eliascoelho911.notas.database.repository.NotaRepository
import br.com.eliascoelho911.notas.model.Nota
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

open class FormularioViewModel : ViewModel() {
    private val repository: NotaRepository by inject(NotaRepository::class.java)
    internal lateinit var notaData: NotaData

    open fun salvar(nota: Nota) {
        viewModelScope.launch {
            repository.salva(nota)
        }
    }

    fun deleta(nota: Nota) {
        viewModelScope.launch {
            repository.deleta(nota)
        }
    }
}