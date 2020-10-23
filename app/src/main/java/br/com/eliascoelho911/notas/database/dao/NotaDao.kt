package br.com.eliascoelho911.notas.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.eliascoelho911.notas.model.Nota

@Dao
interface NotaDao {
    @Insert
    fun salva(nota: Nota)

    @Query("SELECT * FROM Nota")
    fun todas(): LiveData<List<Nota>>
}