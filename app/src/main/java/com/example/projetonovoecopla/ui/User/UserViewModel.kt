package com.example.projetonovoecopla.ui.User

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Perfil"
    }
    val text: LiveData<String> = _text
}