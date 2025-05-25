package com.example.projetonovoecopla.data.models


data class PedidoItem(
    val id: String? = null,
    val produtoId: String,
    val nomeProduto: String,
    val quantidade: Int,
    val precoUnitario: Double
)
