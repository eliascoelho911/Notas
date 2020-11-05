package br.com.eliascoelho911.notas.database.repository

import br.com.eliascoelho911.notas.database.dao.NotaDao
import br.com.eliascoelho911.notas.model.Nota
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class NotaRepository {
    private val dao: NotaDao by inject(NotaDao::class.java)

    suspend fun salva(nota: Nota) {
        withContext(IO) {
            dao.salva(nota)
        }
    }

    suspend fun deleta(nota: Nota) {
        withContext(IO) {
            dao.deleta(nota)
        }
    }
}