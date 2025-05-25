package com.example.projetonovoecopla.data.repository

import android.content.Context
import com.example.projetonovoecopla.data.network.AuthService
import com.example.projetonovoecopla.data.models.Cart
import com.example.projetonovoecopla.data.models.CartItem
import com.example.projetonovoecopla.data.network.RetrofitClient
import retrofit2.Call

class CartRepository(private val context: Context) {

    private val api = RetrofitClient.createAuthService(context)

    fun getCart(): Call<Cart> = api.getCart()

    fun addItem(item: CartItem): Call<Cart> = api.addItem(item)

    fun updateItem(item: CartItem): Call<Cart> = api.updateItem(item)

    fun removeItem(productId: String): Call<Cart> = api.removeItem(productId)

    fun clearCart(): Call<Void> = api.clearCart()
}