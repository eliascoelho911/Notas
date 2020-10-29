package br.com.eliascoelho911.notas.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.eliascoelho911.notas.database.converter.DescricaoConverter
import br.com.eliascoelho911.notas.database.dao.NotaDao
import br.com.eliascoelho911.notas.model.Nota

@TypeConverters(DescricaoConverter::class)
@Database(entities = [Nota::class], version = 2, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getNotaDao() : NotaDao
}