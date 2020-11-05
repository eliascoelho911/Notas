package br.com.eliascoelho911.notas.ui.notas

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.eliascoelho911.notas.database.repository.NotaRepository
import br.com.eliascoelho911.notas.model.Nota
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class NotasViewModel : ViewModel() {
    private val repository: NotaRepository by inject(NotaRepository::class.java)
    private lateinit var notas: LiveData<List<Nota>>

    fun todas(quandoBuscar: (LiveData<List<Nota>>) -> Unit) {
        if (!::notas.isInitialized) {
            viewModelScope.launch {
                val notasRetornadas = repository.todas()
                notas = notasRetornadas
                quandoBuscar(notas)
            }
        } else {
            quandoBuscar(notas)
        }
    }
}