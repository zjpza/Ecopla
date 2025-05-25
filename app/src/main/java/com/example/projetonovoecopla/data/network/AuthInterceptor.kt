package com.example.projetonovoecopla.data.network

import android.content.Context
import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("ecopla_prefs", Context.MODE_PRIVATE)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val token = sharedPreferences.getString("jwt_token", null)

        token?.let {
            val requestUrl = chain.request().url.toString()
            if (!requestUrl.contains("/api/auth/login") && !requestUrl.contains("/api/auth/signup")) {
                 requestBuilder.addHeader("Authorization", "Bearer $it")
            }
        }

        return chain.proceed(requestBuilder.build())
    }
}
