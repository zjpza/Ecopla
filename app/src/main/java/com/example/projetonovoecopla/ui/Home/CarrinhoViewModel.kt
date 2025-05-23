package com.example.projetonovoecopla.ui.Carrinho

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.projetonovoecopla.data.models.Cart
import com.example.projetonovoecopla.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarrinhoViewModel(application: Application) : AndroidViewModel(application) {

    val subtotal = MutableLiveData<String>("R$ 0,00")
    val frete = MutableLiveData<String>("R$ 0,00")
    val total = MutableLiveData<String>("R$ 0,00")

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    fun fetchCart() {
        val api = RetrofitClient.createAuthService(context)
        api.getCart().enqueue(object : Callback<Cart> {
            override fun onResponse(call: Call<Cart>, response: Response<Cart>) {
                if (response.isSuccessful) {
                    response.body()?.let { cart ->
                        subtotal.value = "R$ %.2f".format(cart.subtotal)
                        frete.value = "R$ %.2f".format(cart.frete)
                        total.value = "R$ %.2f".format(cart.total)

                    }
                }
            }

            override fun onFailure(call: Call<Cart>, t: Throwable) {

            }
        })
    }
}
