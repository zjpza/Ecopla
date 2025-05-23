package com.example.projetonovoecopla.ui.Encomenda

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projetonovoecopla.data.models.Pedido
import com.example.projetonovoecopla.data.repository.PedidoRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EncomendaViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = PedidoRepository(application)

    val pedidos = MutableLiveData<List<Pedido>>()
    val pedidoDetalhe = MutableLiveData<Pedido>()
    val error = MutableLiveData<String>()

    private val _text = MutableLiveData<String>().apply {
        value = "Adicione os arquivos do seu pedido."
    }
    val text: LiveData<String> = _text

    fun criarPedido() {
        repo.criarPedido().enqueue(object : Callback<Pedido> {
            override fun onResponse(call: Call<Pedido>, response: Response<Pedido>) {
                if (response.isSuccessful) {
                    pedidoDetalhe.value = response.body()
                } else {
                    error.value = "Erro ao criar pedido"
                }
            }

            override fun onFailure(call: Call<Pedido>, t: Throwable) {
                error.value = "Falha: ${t.message}"
            }
        })
    }

    fun listarMeusPedidos() {
        repo.listarMeusPedidos().enqueue(object : Callback<List<Pedido>> {
            override fun onResponse(call: Call<List<Pedido>>, response: Response<List<Pedido>>) {
                if (response.isSuccessful) {
                    pedidos.value = response.body()
                } else {
                    error.value = "Erro ao buscar pedidos"
                }
            }

            override fun onFailure(call: Call<List<Pedido>>, t: Throwable) {
                error.value = "Falha: ${t.message}"
            }
        })
    }

    // Métodos para getPedido e listarTodos similares
}