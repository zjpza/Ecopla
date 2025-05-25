package com.example.projetonovoecopla.data.models

data class Cart(
    val userId: String,
    val items: List<CartItem>,
    val subtotal: Double,
    val frete: Double,
    val total: Double
)
