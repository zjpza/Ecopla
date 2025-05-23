package com.example.projetonovoecopla.ui.Cart

import android.app.Application
import androidx.lifecycle.*
import com.example.projetonovoecopla.data.models.Cart
import com.example.projetonovoecopla.data.models.CartItem
import com.example.projetonovoecopla.data.repository.CartRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = CartRepository(application)

    val cart = MutableLiveData<Cart>()
    val error = MutableLiveData<String>()

    fun fetchCart() {
        repo.getCart().enqueue(object : Callback<Cart> {
            override fun onResponse(call: Call<Cart>, response: Response<Cart>) {
                if (response.isSuccessful) {
                    cart.value = response.body()
                } else {
                    error.value = "Erro ao carregar carrinho"
                }
            }

            override fun onFailure(call: Call<Cart>, t: Throwable) {
                error.value = "Falha: ${t.message}"
            }
        })
    }

    // Adicione métodos para addItem, updateItem, etc, da mesma forma
}