package br.com.eliascoelho911.notas.ui.formulario

import androidx.lifecycle.MutableLiveData
import br.com.eliascoelho911.notas.model.Marcador
import br.com.eliascoelho911.notas.model.Nota
import br.com.eliascoelho911.notas.model.NotaCompleta

class NotaData(
    notaCompleta: NotaCompleta,
) {
    val titulo: MutableLiveData<String> = MutableLiveData(notaCompleta.nota.titulo)
    val descricao: MutableLiveData<String?> = MutableLiveData(notaCompleta.nota.descricao)
    val cor: MutableLiveData<Int> = MutableLiveData(notaCompleta.nota.cor)
    val id = notaCompleta.nota.id
    val marcador: MutableLiveData<Marcador?> = MutableLiveData(notaCompleta.marcador)

    fun paraNotaCompleta() = NotaCompleta(
        Nota(
            id = id,
            titulo = titulo.value!!,
            descricao = descricao.value ?: "",
            cor = cor.value!!,
            idMarcador = marcador.value?.id
        ),
        marcador.value)
}