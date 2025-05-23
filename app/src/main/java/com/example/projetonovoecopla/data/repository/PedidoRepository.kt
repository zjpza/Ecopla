package com.example.projetonovoecopla.data.repository

import android.content.Context
import com.example.projetonovoecopla.data.network.RetrofitClient

class PedidoRepository(context: Context) {
    private val api = RetrofitClient.createAuthService(context)

    fun criarPedido() = api.criarPedido()

    fun listarMeusPedidos() = api.listarMeusPedidos()

    fun getPedido(id: String) = api.getPedido(id)

    fun listarTodos() = api.listarTodos()
}
