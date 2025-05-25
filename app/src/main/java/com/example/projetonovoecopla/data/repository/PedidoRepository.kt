package com.example.projetonovoecopla.data.repository

import android.content.Context
import com.example.projetonovoecopla.data.models.Pedido
import com.example.projetonovoecopla.data.network.ApiService
import com.example.projetonovoecopla.data.network.BaseRepository
import com.example.projetonovoecopla.data.network.ResultWrapper
import com.example.projetonovoecopla.data.network.RetrofitInstance

class PedidoRepository(context: Context) : BaseRepository() {

    private val apiService: ApiService = RetrofitInstance.getInstance(context).getApiService()

    suspend fun createPedido(): ResultWrapper<Pedido> {
        return safeApiCall { apiService.createPedido() }
    }

    suspend fun getMyPedidos(): ResultWrapper<List<Pedido>> {
        return safeApiCall { apiService.getMyPedidos() }
    }

    suspend fun getPedidoById(id: String): ResultWrapper<Pedido> {
        return safeApiCall { apiService.getPedidoById(id) }
    }

}
