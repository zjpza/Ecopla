package com.example.projetonovoecopla.data.models


data class Cart(
    val id: String? = null,
    val userId: String,
    val items: List<CartItem>,
    val total: Double? = null
)
