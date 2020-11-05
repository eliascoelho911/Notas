package br.com.eliascoelho911.notas.model

import androidx.annotation.ColorInt
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.ui.util.getColor
import java.io.Serializable

@Entity
data class Nota(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val titulo: String = "",
    val descricao: String? = "",
    @ColorInt
    val cor: Int = getColor(R.color.background_tela),
    val idMarcador: Long? = null,
) : Serializable
