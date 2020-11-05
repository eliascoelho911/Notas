package br.com.eliascoelho911.notas.ui.formulario

import androidx.lifecycle.MutableLiveData
import br.com.eliascoelho911.notas.model.Nota

class NotaData(
    private val nota: Nota,
) {
    val titulo: MutableLiveData<String> = MutableLiveData(nota.titulo)
    val descricao: MutableLiveData<String?> = MutableLiveData(nota.descricao)
    val taskList: MutableLiveData<List<String>?> = MutableLiveData(nota.taskList)
    val cor: MutableLiveData<Int> = MutableLiveData(nota.cor)

    fun paraNota() = Nota(id = nota.id,
        titulo = titulo.value!!,
        descricao = descricao.value ?: "",
        cor = cor.value!!,
        taskList = taskList.value ?: listOf())
}