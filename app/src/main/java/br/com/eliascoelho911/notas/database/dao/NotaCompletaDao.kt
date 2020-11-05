package br.com.eliascoelho911.notas.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.eliascoelho911.notas.model.NotaCompleta

@Dao
interface NotaCompletaDao {
    @Transaction
    @Query("SELECT * FROM Nota ORDER BY Nota.id DESC")
    fun todos(): LiveData<List<NotaCompleta>>
}