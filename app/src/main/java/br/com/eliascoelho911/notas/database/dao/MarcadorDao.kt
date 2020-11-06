package br.com.eliascoelho911.notas.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import br.com.eliascoelho911.notas.model.Marcador

@Dao
interface MarcadorDao {
    @Query("SELECT * FROM Marcador")
    fun todos(): LiveData<List<Marcador>>
}