package br.com.eliascoelho911.notas.ui.formulario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.eliascoelho911.notas.database.repository.NotaRepository
import br.com.eliascoelho911.notas.model.Nota
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class FormularioViewModel : ViewModel() {
    private val repository: NotaRepository by KoinJavaComponent.inject(NotaRepository::class.java)

    fun salvar(nota: Nota) {
        viewModelScope.launch {
            repository.salva(nota)
        }
    }
}