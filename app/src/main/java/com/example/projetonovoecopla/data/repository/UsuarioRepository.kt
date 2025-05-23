package com.example.projetonovoecopla.data.repository

import android.content.Context
import com.example.projetonovoecopla.data.models.Usuario
import com.example.projetonovoecopla.data.network.RetrofitClient
import retrofit2.Call

class UsuarioRepository(context: Context) {
    private val api = RetrofitClient.createAuthService(context)

    fun listarUsuarios() = api.listarUsuarios()
    fun buscarUsuario(id: String) = api.buscarUsuario(id)
    fun atualizarUsuario(id: String, usuario: Usuario) = api.atualizarUsuario(id, usuario)
    fun deletarUsuario(id: String) = api.deletarUsuario(id)

    fun buscarUsuarioAtual(): Call<Usuario> {
        return api.getUsuarioAtual()
    }
}