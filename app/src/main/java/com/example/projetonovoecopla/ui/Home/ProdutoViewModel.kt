package com.example.projetonovoecopla.ui.Home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projetonovoecopla.data.models.Produto
import com.example.projetonovoecopla.data.repository.ProdutoRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdutoViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = ProdutoRepository(application.applicationContext)

    val produtos = MutableLiveData<List<Produto>>()
    val error = MutableLiveData<String>()

    fun carregarProdutos() {
        repo.listarProdutos().enqueue(object : Callback<List<Produto>> {
            override fun onResponse(call: Call<List<Produto>>, response: Response<List<Produto>>) {
                if (response.isSuccessful) {
                    produtos.value = response.body()
                } else {
                    error.value = "Erro ao carregar produtos"
                }
            }

            override fun onFailure(call: Call<List<Produto>>, t: Throwable) {
                error.value = "Falha: ${t.message}"
            }
        })
    }

    // Métodos para buscar, criar, atualizar e deletar podem ser criados da mesma forma
}