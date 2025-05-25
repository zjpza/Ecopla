package com.example.projetonovoecopla.data.repository

import android.content.Context
import com.example.projetonovoecopla.data.models.Usuario
import com.example.projetonovoecopla.data.network.ApiService
import com.example.projetonovoecopla.data.network.BaseRepository
import com.example.projetonovoecopla.data.network.ResultWrapper
import com.example.projetonovoecopla.data.network.RetrofitInstance

class UsuarioRepository(context: Context) : BaseRepository() {

    private val apiService: ApiService = RetrofitInstance.getInstance(context).getApiService()
    private val authRepository = AuthRepository(context)

    suspend fun getMyUsuario(): ResultWrapper<Usuario> {
        val userId = authRepository.getUserId()
        return if (userId != null) {
            safeApiCall { apiService.getUsuario(userId) }
        } else {
            ResultWrapper.GenericError(message = "User not logged in")
        }
    }

    suspend fun updateMyUsuario(usuario: Usuario): ResultWrapper<Usuario> {
        val userId = authRepository.getUserId()
        return if (userId != null) {

            safeApiCall { apiService.updateUsuario(userId, usuario) }
        } else {
            ResultWrapper.GenericError(message = "User not logged in")
        }
    }

    suspend fun getUsuarioById(id: String): ResultWrapper<Usuario> {
        return safeApiCall { apiService.getUsuario(id) }
    }
}
