package com.example.projetonovoecopla.data.models

import java.util.Date

data class Pedido(
    val id: String? = null,
    val userId: String,
    val items: List<PedidoItem>,
    val total: Double,
    val dataPedido: Date? = null,
    val status: String? = null
)
