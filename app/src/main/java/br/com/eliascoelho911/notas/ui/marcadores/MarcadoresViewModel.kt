package br.com.eliascoelho911.notas.ui.marcadores

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.eliascoelho911.notas.database.repository.MarcadorRepository
import br.com.eliascoelho911.notas.model.Marcador
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class MarcadoresViewModel : ViewModel() {
    private val marcadorRepository by inject(MarcadorRepository::class.java)

    fun todos(quandoBuscar: (LiveData<List<Marcador>>) -> Unit) {
        viewModelScope.launch {
            quandoBuscar(marcadorRepository.todos())
        }
    }
}