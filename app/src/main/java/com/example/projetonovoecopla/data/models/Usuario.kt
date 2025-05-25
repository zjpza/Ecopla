package com.example.projetonovoecopla.data.models

data class Usuario(
    val id: String,
    val nome: String,
    val email: String,
    val endereco: String,
    val telefone: String?,
    val cep: String
)