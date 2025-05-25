package com.example.projetonovoecopla.data.models

data class Usuario(
    val id: String? = null,
    val nome: String,
    val email: String,
    val idade: Int,
    val senha: String? = null,
    val roles: List<String>? = null
)
