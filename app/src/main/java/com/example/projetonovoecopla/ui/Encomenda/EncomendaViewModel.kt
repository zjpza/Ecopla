package com.example.projetonovoecopla.ui.Encomenda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EncomendaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Adicione os arquivos do seu pedido"
    }
    val text: LiveData<String> = _text
}