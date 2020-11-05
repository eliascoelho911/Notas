package br.com.eliascoelho911.notas.model

import androidx.room.Embedded
import androidx.room.Relation
import java.io.Serializable

data class NotaCompleta(
    @Embedded
    val nota: Nota = Nota(),
    @Relation(
        parentColumn = "idMarcador",
        entityColumn = "id")
    val marcador: Marcador? = null,
) : Serializable