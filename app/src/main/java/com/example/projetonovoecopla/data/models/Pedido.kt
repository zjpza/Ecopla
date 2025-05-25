package com.example.projetonovoecopla.data.models

data class Pedido(
    val id: String,
    val userId: String,
    val items: List<CartItem>,
    val status: String,
    val total: Double,
    val createdAt: String
)

data class PedidoItem(
    val productId: String,
    val nome: String,
    val precoUnitario: Double,
    val quantidade: Int
)
