package com.example.projetonovoecopla.ui.Venda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VendaViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Adicione as fotos dos materiais plásticos que deseja vender."
    }
    val text: LiveData<String> = _text
}