package com.example.projetonovoecopla.data.models

data class LoginRequest(
    val email: String,
    val senha: String
)

data class JwtResponse(
    val token: String,
    val tipo: String,
    val id: String,
    val nome: String,
    val email: String
)
