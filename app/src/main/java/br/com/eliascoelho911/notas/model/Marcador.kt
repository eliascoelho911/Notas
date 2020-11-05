package br.com.eliascoelho911.notas.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Marcador(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val nome: String,
)