package com.example.projetonovoecopla.data.repository

import android.content.Context
import com.example.projetonovoecopla.data.models.Cart
import com.example.projetonovoecopla.data.models.CartItem
import com.example.projetonovoecopla.data.network.ApiService
import com.example.projetonovoecopla.data.network.BaseRepository
import com.example.projetonovoecopla.data.network.ResultWrapper
import com.example.projetonovoecopla.data.network.RetrofitInstance

class CartRepository(context: Context) : BaseRepository() {

    private val apiService: ApiService = RetrofitInstance.getInstance(context).getApiService()

    suspend fun getCart(): ResultWrapper<Cart> {
        return safeApiCall { apiService.getCart() }
    }

    suspend fun addItem(item: CartItem): ResultWrapper<Cart> {
        return safeApiCall { apiService.addItemToCart(item) }
    }

    suspend fun updateItem(item: CartItem): ResultWrapper<Cart> {
        return safeApiCall { apiService.updateCartItem(item) }
    }

    suspend fun removeItem(productId: String): ResultWrapper<Cart> {
        return safeApiCall { apiService.removeCartItem(productId) }
    }

    suspend fun clearCart(): ResultWrapper<Void> {
        return safeApiCall { apiService.clearCart() }
    }
}
