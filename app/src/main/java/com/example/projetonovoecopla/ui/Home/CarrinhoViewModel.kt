package com.example.projetonovoecopla.ui.Carrinho

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarrinhoViewModel : ViewModel() {

    private val _subtotal = MutableLiveData<String>().apply {
        value = "Subtotal: R$408,50"
    }
    val subtotal: LiveData<String> = _subtotal

    private val _frete = MutableLiveData<String>().apply {
        value = "Frete: R$15,00"
    }
    val frete: LiveData<String> = _frete

    private val _total = MutableLiveData<String>().apply {
        value = "Total: R$423,50"
    }
    val total: LiveData<String> = _total
}
