package com.example.projetonovoecopla.data.network

import android.content.Context
import com.example.projetonovoecopla.util.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private fun getToken(context: Context): String? {
        val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        return prefs.getString("jwt_token", null)
    }

    fun createAuthService(context: Context): AuthService {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor { getToken(context) })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(AuthService::class.java)
    }
}
