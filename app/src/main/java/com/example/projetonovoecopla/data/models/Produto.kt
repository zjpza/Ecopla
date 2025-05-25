package com.example.projetonovoecopla.data.models

import com.google.gson.annotations.SerializedName

data class Produto(
    val id: String? = null,
    val nome: String,
    val descricao: String? = null,
    val preco: Double,
    val material: String? = null,
    val cor: String? = null,
    val estoque: Int? = 0
)
