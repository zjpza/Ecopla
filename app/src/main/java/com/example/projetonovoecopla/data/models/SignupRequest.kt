package com.example.projetonovoecopla.data.models

data class SignupRequest(
    val nome: String,
    val email: String,
    val senha: String,
    val idade: Int
)
