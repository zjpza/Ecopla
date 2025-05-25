package com.example.projetonovoecopla.ui.Home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.projetonovoecopla.data.models.Produto
import com.example.projetonovoecopla.data.network.ResultWrapper
import com.example.projetonovoecopla.data.repository.ProdutoRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val produtoRepository = ProdutoRepository(application)

    private val _produtos = MutableLiveData<ResultWrapper<List<Produto>>>()
    val produtos: LiveData<ResultWrapper<List<Produto>>> = _produtos

    private val _filteredProdutos = MutableLiveData<ResultWrapper<List<Produto>>>()
    val filteredProdutos: LiveData<ResultWrapper<List<Produto>>> = _filteredProdutos

    fun fetchProdutos() {
        viewModelScope.launch {
            _produtos.value = produtoRepository.getProdutos()
        }
    }

    fun applyFilter(
        nome: String? = null,
        minPrice: Double? = null,
        maxPrice: Double? = null,
        material: String? = null,
        cor: String? = null
    ) {
        viewModelScope.launch {
            _filteredProdutos.value = produtoRepository.searchProdutos(nome, minPrice, maxPrice, material, cor)

        }
    }

    fun clearFilter() {
         fetchProdutos()

    }
}
