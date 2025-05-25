package com.example.projetonovoecopla.data.models

// Based on record JwtResponse in AuthController.java
data class AuthResponse(
    val token: String,
    val tipo: String,
    val id: String,
    val nome: String,
    val email: String
)
