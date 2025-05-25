package com.example.projetonovoecopla.ui.Home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.projetonovoecopla.data.models.Cart
import com.example.projetonovoecopla.data.models.CartItem
import com.example.projetonovoecopla.data.models.Produto // Assuming Produto needed to add to cart
import com.example.projetonovoecopla.data.network.ResultWrapper
import com.example.projetonovoecopla.data.repository.CartRepository
import kotlinx.coroutines.launch

class CarrinhoViewModel(application: Application) : AndroidViewModel(application) {

    private val cartRepository = CartRepository(application)

    private val _cart = MutableLiveData<ResultWrapper<Cart>>()
    val cart: LiveData<ResultWrapper<Cart>> = _cart

    private val _cartUpdateResult = MutableLiveData<ResultWrapper<Cart>>()
    val cartUpdateResult: LiveData<ResultWrapper<Cart>> = _cartUpdateResult

    private val _clearCartResult = MutableLiveData<ResultWrapper<Void>>()
    val clearCartResult: LiveData<ResultWrapper<Void>> = _clearCartResult

    fun fetchCart() {
        viewModelScope.launch {
            _cart.value = cartRepository.getCart()
        }
    }

    fun addItemToCart(produto: Produto, quantidade: Int) {
        viewModelScope.launch {
            produto.id?.let {
                val item = CartItem(productId = it, nome = produto.nome, preco = produto.preco, quantidade = quantidade)
                _cartUpdateResult.value = cartRepository.addItem(item)

            } ?: run {
                 _cartUpdateResult.value = ResultWrapper.GenericError(message = "Product ID is missing")
            }
        }
    }


    fun updateCartItemQuantity(productId: String, newQuantity: Int) {
         viewModelScope.launch {
             val item = CartItem(productId = productId, nome = "", preco = 0.0, quantidade = newQuantity)
             _cartUpdateResult.value = cartRepository.updateItem(item)

         }
    }

    fun removeItemFromCart(productId: String) {
        viewModelScope.launch {
            _cartUpdateResult.value = cartRepository.removeItem(productId)

        }
    }

    fun clearCart() {
        viewModelScope.launch {
            _clearCartResult.value = cartRepository.clearCart()

        }
    }
}
