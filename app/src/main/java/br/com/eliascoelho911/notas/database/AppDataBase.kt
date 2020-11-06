package br.com.eliascoelho911.notas.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.eliascoelho911.notas.database.converter.ListStringConverter
import br.com.eliascoelho911.notas.database.dao.MarcadorDao
import br.com.eliascoelho911.notas.database.dao.NotaCompletaDao
import br.com.eliascoelho911.notas.database.dao.NotaDao
import br.com.eliascoelho911.notas.model.Marcador
import br.com.eliascoelho911.notas.model.Nota

@TypeConverters(ListStringConverter::class)
@Database(entities = [Nota::class, Marcador::class], version = 5, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getNotaDao(): NotaDao
    abstract fun getNotaCompletaDao(): NotaCompletaDao
    abstract fun getMarcadorDao(): MarcadorDao
}