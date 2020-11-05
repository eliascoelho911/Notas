package br.com.eliascoelho911.notas.ui.notas

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.eliascoelho911.notas.database.repository.NotaCompletaRepository
import br.com.eliascoelho911.notas.model.NotaCompleta
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class NotasViewModel : ViewModel() {
    private val repository: NotaCompletaRepository by inject(NotaCompletaRepository::class.java)
    private lateinit var notas: LiveData<List<NotaCompleta>>

    fun todas(quandoBuscar: (LiveData<List<NotaCompleta>>) -> Unit) {
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