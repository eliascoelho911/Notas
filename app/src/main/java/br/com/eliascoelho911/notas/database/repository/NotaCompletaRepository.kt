package br.com.eliascoelho911.notas.database.repository

import androidx.lifecycle.LiveData
import br.com.eliascoelho911.notas.database.dao.NotaCompletaDao
import br.com.eliascoelho911.notas.model.NotaCompleta
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class NotaCompletaRepository {
    private val dao: NotaCompletaDao by inject(NotaCompletaDao::class.java)

    suspend fun todos(): LiveData<List<NotaCompleta>> {
        return withContext(IO) {
            dao.todos()
        }
    }
}