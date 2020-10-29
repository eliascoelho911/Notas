package br.com.eliascoelho911.notas.database.converter

import androidx.room.TypeConverter
import br.com.eliascoelho911.notas.model.Tasklist
import br.com.eliascoelho911.notas.model.Descricao
import br.com.eliascoelho911.notas.model.Texto

private const val SEPARADOR_CHECKBOX = "\n"
private const val PREFIXO_CHECKBOX = "["
private const val SUFIXO_CHECKBOX = "]"

class DescricaoConverter {
    @TypeConverter
    fun paraString(descricao: Descricao): String {
        return if (descricao is Texto) {
            descricao.valor()
        } else {
            @Suppress("UNCHECKED_CAST") val list = descricao.valor() as List<String>
            "$PREFIXO_CHECKBOX${list.joinToString(separator = SEPARADOR_CHECKBOX)}$SUFIXO_CHECKBOX"
        }
    }

    @TypeConverter
    fun paraDescricao(valor: String): Descricao {
        val prefixoDoValor = valor.substring(IntRange(0, 0))
        val sufixoDoValor = valor.substring(valor.length - 1)
        return if (prefixoDoValor == PREFIXO_CHECKBOX &&
            sufixoDoValor == SUFIXO_CHECKBOX
        ) {
            val valorSemPrefixoESufixo = valor.substring(IntRange(1, valor.length - 2))
            Tasklist(valorSemPrefixoESufixo.split(SEPARADOR_CHECKBOX))
        } else {
            Texto(valor)
        }
    }
}
