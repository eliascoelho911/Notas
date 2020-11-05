package br.com.eliascoelho911.notas.model

import androidx.room.Embedded
import androidx.room.Relation

data class NotaCompleta(
    @Embedded
    val nota: Nota,
    @Relation(
        parentColumn = "idMarcador",
        entityColumn = "id")
    val marcador: Marcador?,
)