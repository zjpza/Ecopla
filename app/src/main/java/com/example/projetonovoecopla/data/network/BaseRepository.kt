package com.example.projetonovoecopla.data.network

import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ResultWrapper<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                response.body()?.let {
                    ResultWrapper.Success(it)
                } ?: ResultWrapper.GenericError(response.code(), "Response body is null")
            } else {
                ResultWrapper.GenericError(response.code(), response.message() ?: "Unknown API error")
            }
        } catch (e: IOException) {
            ResultWrapper.NetworkError("Network error: ${e.message}")
        } catch (e: Exception) {
            ResultWrapper.GenericError(null, "Unexpected error: ${e.message}")
        }
    }
}

sealed class ResultWrapper<out T : Any> {
    data class Success<out T : Any>(val value: T) : ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val message: String? = null) : ResultWrapper<Nothing>()
    data class NetworkError(val message: String) : ResultWrapper<Nothing>()
}
