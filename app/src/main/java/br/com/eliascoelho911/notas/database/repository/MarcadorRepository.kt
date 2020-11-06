package br.com.eliascoelho911.notas.database.repository

import androidx.lifecycle.LiveData
import br.com.eliascoelho911.notas.database.dao.MarcadorDao
import br.com.eliascoelho911.notas.model.Marcador
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class MarcadorRepository {
    private val dao by inject(MarcadorDao::class.java)

    suspend fun todos(): LiveData<List<Marcador>> {
        return withContext(IO) {
            dao.todos()
        }
    }
}