package br.com.eliascoelho911.notas.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import br.com.eliascoelho911.notas.model.NotaCompleta

@Dao
interface NotaCompletaDao {
    @Query("SELECT * FROM Nota")
    fun todos(): LiveData<List<NotaCompleta>>
}