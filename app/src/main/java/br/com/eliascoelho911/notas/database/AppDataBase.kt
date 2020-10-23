package br.com.eliascoelho911.notas.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.eliascoelho911.notas.database.dao.NotaDao
import br.com.eliascoelho911.notas.model.Nota

@Database(entities = [Nota::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getNotaDao() : NotaDao
}