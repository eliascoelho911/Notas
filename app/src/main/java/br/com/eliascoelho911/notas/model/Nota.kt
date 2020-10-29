package br.com.eliascoelho911.notas.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Nota(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val titulo: String = "Titulo",
    val descricao: Descricao = Texto("Descricao"),
)

interface Descricao {
    fun valor(): Any
}

class Texto(private val s: String) : Descricao {
    override fun valor(): String {
        return s;
    }
}

class Tasklist(private val list: List<String>) : Descricao {
    override fun valor(): List<String> {
        return list
    }
}