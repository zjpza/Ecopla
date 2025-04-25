package com.example.projetonovoecopla.ui.Venda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DialogViewModelVenda : ViewModel(){
    private val _text = MutableLiveData<String>().apply {
        value = "Deseja continuar com sua venda?"
    }
    val text: LiveData<String> = _text
}