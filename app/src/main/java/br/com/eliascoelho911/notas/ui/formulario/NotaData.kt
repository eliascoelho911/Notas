package br.com.eliascoelho911.notas.ui.formulario

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.model.Nota
import br.com.eliascoelho911.notas.ui.util.getColor

class NotaData(
    nota: Nota
) {
    val titulo = MutableLiveData(nota.titulo)
    val descricao: MutableLiveData<String?> = MutableLiveData(nota.descricao)
    val taskList: MutableLiveData<List<String>?> = MutableLiveData(nota.taskList)
    val cor = MutableLiveData(nota.cor)

    fun paraNota() = Nota(titulo = titulo.value ?: "",
        descricao = descricao.value ?: "",
        cor = cor.value ?: getColor(R.color.colorPrimaryDark),
        taskList = taskList.value ?: listOf())
}