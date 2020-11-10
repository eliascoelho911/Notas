package br.com.eliascoelho911.notas.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Marcador(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String = "",
) : Serializable