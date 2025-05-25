package com.example.projetonovoecopla.ui.Login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.projetonovoecopla.data.models.AuthRequest
import com.example.projetonovoecopla.data.models.AuthResponse
import com.example.projetonovoecopla.data.models.SignupRequest
import com.example.projetonovoecopla.data.models.Usuario
import com.example.projetonovoecopla.data.network.ResultWrapper
import com.example.projetonovoecopla.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val authRepository = AuthRepository(application)

    private val _loginResult = MutableLiveData<ResultWrapper<AuthResponse>>()
    val loginResult: LiveData<ResultWrapper<AuthResponse>> = _loginResult

    private val _signupResult = MutableLiveData<ResultWrapper<Usuario>>()
    val signupResult: LiveData<ResultWrapper<Usuario>> = _signupResult

    fun login(email: String, senha: String) {
        viewModelScope.launch {
            val request = AuthRequest(email, senha)
            _loginResult.value = authRepository.login(request)
        }
    }

    fun signup(nome: String, email: String, senha: String, idade: Int) {
        viewModelScope.launch {
            val request = SignupRequest(nome, email, senha, idade)
            _signupResult.value = authRepository.signup(request)
        }
    }

    fun isLoggedIn(): Boolean {
        return authRepository.isLoggedIn()
    }

    fun logout() {
        authRepository.logout()
    }
}
