package br.com.eliascoelho911.notas.database.converter

import androidx.room.TypeConverter

class ListStringConverter {
    @TypeConverter
    fun paraString(list: List<String>?): String? {
        return list?.joinToString()
    }

    @TypeConverter
    fun paraList(string: String?): List<String>? {
        return string?.split(", ")
    }
}