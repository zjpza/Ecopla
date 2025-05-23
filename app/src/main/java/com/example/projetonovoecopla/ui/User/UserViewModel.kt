package com.example.projetonovoecopla.ui.User

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projetonovoecopla.data.models.Usuario
import com.example.projetonovoecopla.data.repository.UsuarioRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = UsuarioRepository(application)

    val usuarios = MutableLiveData<List<Usuario>>()
    val usuario = MutableLiveData<Usuario>()
    val error = MutableLiveData<String>()
    private val _text = MutableLiveData<String>("Perfil")
    val text: LiveData<String> get() = _text

    fun carregarUsuarios() {
        repo.listarUsuarios().enqueue(object : Callback<List<Usuario>> {
            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                if (response.isSuccessful) {
                    usuarios.value = response.body()
                } else {
                    error.value = "Erro ao listar usuários"
                }
            }

            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
                error.value = "Falha: ${t.message}"
            }
        })
    }

}
