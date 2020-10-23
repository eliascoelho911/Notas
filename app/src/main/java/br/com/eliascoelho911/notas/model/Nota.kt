package br.com.eliascoelho911.notas.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Nota(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val titulo: String = "Titulo",
    val descricao: String = "Descrição"
)