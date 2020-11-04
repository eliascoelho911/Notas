package br.com.eliascoelho911.notas.model

import androidx.annotation.ColorInt
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.ui.util.getColor

@Entity
data class Nota(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val titulo: String = "",
    val descricao: String? = "",
    val taskList: List<String>? = null,
    @ColorInt
    val cor: Int = getColor(R.color.colorPrimaryDark)
)
