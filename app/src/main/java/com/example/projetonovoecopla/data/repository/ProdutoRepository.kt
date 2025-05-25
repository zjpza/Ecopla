package com.example.projetonovoecopla.data.repository

import android.content.Context
import com.example.projetonovoecopla.data.models.Produto
import com.example.projetonovoecopla.data.network.RetrofitClient

class ProdutoRepository(context: Context) {
    private val api = RetrofitClient.createAuthService(context)

    fun listarProdutos() = api.listarProdutos()
    fun buscarProdutos(nome: String?, minPrice: Double?, maxPrice: Double?, material: String?, cor: String?) =
        api.buscarProdutos(nome, minPrice, maxPrice, material, cor)

    fun buscarProdutoPorId(id: String) = api.buscarProdutoPorId(id)

    fun criarProduto(produto: Produto) = api.criarProduto(produto)

    fun atualizarProduto(id: String, produto: Produto) = api.atualizarProduto(id, produto)

    fun deletarProduto(id: String) = api.deletarProduto(id)
}