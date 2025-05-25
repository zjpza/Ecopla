package com.example.projetonovoecopla.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.projetonovoecopla.data.models.AuthRequest
import com.example.projetonovoecopla.data.models.AuthResponse
import com.example.projetonovoecopla.data.models.SignupRequest
import com.example.projetonovoecopla.data.models.Usuario
import com.example.projetonovoecopla.data.network.ApiService
import com.example.projetonovoecopla.data.network.BaseRepository
import com.example.projetonovoecopla.data.network.ResultWrapper
import com.example.projetonovoecopla.data.network.RetrofitInstance

class AuthRepository(context: Context) : BaseRepository() {

    private val apiService: ApiService = RetrofitInstance.getInstance(context).getApiService()
    private val sharedPreferences: SharedPreferences = 
        context.getSharedPreferences("ecopla_prefs", Context.MODE_PRIVATE)

    suspend fun login(request: AuthRequest): ResultWrapper<AuthResponse> {
        val result = safeApiCall { apiService.login(request) }
        if (result is ResultWrapper.Success) {
            saveAuthInfo(result.value)
        }
        return result
    }

    suspend fun signup(request: SignupRequest): ResultWrapper<Usuario> {
        return safeApiCall { apiService.signup(request) }
    }

    private fun saveAuthInfo(authResponse: AuthResponse) {
        with(sharedPreferences.edit()) {
            putString("jwt_token", authResponse.token)
            putString("user_id", authResponse.id)
            putString("user_name", authResponse.nome)
            putString("user_email", authResponse.email)
            apply()
        }
    }

    fun logout() {
        with(sharedPreferences.edit()) {
            remove("jwt_token")
            remove("user_id")
            remove("user_name")
            remove("user_email")
            apply()
        }
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.contains("jwt_token")
    }

    fun getUserId(): String? {
        return sharedPreferences.getString("user_id", null)
    }
}
