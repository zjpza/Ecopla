package com.example.projetonovoecopla.data.models


data class CartItem(
    val productId: String,
    val nome: String,
    val preco: Double,
    var quantidade: Int
)
