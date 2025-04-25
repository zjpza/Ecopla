package com.example.projetonovoecopla.ui.Encomenda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DialogViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Deseja continuar com seu pedido?"
    }
    val text: LiveData<String> = _text
}