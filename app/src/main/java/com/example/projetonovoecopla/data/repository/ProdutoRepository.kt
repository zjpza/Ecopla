package com.example.projetonovoecopla.data.repository

import android.content.Context
import com.example.projetonovoecopla.data.models.Produto // Certifique-se que o import está correto
import com.example.projetonovoecopla.data.network.ApiService
import com.example.projetonovoecopla.data.network.BaseRepository
import com.example.projetonovoecopla.data.network.ResultWrapper
import com.example.projetonovoecopla.data.network.RetrofitInstance
import kotlinx.coroutines.delay // Import para simular delay

class ProdutoRepository(context: Context) : BaseRepository() {

    // Mantém a referência ao ApiService, caso queira voltar a usar a API depois
    private val apiService: ApiService = RetrofitInstance.getInstance(context).getApiService()

    // Flag para controlar se usamos dados mockados ou da API
    private val useMockData = true // Mude para false para usar a API real

    suspend fun getProdutos(): ResultWrapper<List<Produto>> {
        if (useMockData) {
            delay(500)

            val mockedProductList = listOf(
                Produto(
                    id = "mock1", // ID fictício
                    nome = "Filamento PLA Verde Musgo Reciclado",
                    descricao = "Filamento PLA de alta qualidade feito com plástico reciclado.",
                    preco = 99.90,
                    material = "PLA Reciclado",
                    cor = "Verde Musgo",
                    estoque = 50
                ),
                Produto(
                    id = "mock2",
                    nome = "Filamento PETG Azul Translúcido",
                    descricao = "PETG resistente e fácil de imprimir, cor azul vibrante.",
                    preco = 115.50,
                    material = "PETG",
                    cor = "Azul Translúcido",
                    estoque = 30
                ),
                Produto(
                    id = "mock3",
                    nome = "Filamento ABS Preto",
                    descricao = "ABS durável para peças que exigem resistência.",
                    preco = 85.00,
                    material = "ABS",
                    cor = "Preto",
                    estoque = 100
                ),
                Produto(
                    id = "mock4",
                    nome = "Filamento ABS Azul",
                    descricao = "ABS durável para peças que exigem resistência.",
                    preco = 70.00,
                    material = "ABS",
                    cor = "Azul",
                    estoque = 20
                )


                // Adicione mais produtos mockados aqui se desejar
            )

            // Retorna a lista mockada dentro de um ResultWrapper.Success
            return ResultWrapper.Success(mockedProductList)
            // --- FIM MOCK DATA ---

        } else {
            // --- CHAMADA REAL DA API ---
            // Se useMockData for false, chama a API normalmente
            return safeApiCall { apiService.getProdutos() }
            // --- FIM CHAMADA REAL DA API ---
        }
    }

    // As outras funções (searchProdutos, getProdutoById) continuam chamando a API
    // Você pode mocká-las também seguindo o mesmo padrão, se necessário.
    suspend fun searchProdutos(
        nome: String? = null,
        minPrice: Double? = null,
        maxPrice: Double? = null,
        material: String? = null,
        cor: String? = null
    ): ResultWrapper<List<Produto>> {
        // TODO: Implementar mock para busca se necessário, ou manter chamada da API
        return safeApiCall { apiService.searchProdutos(nome, minPrice, maxPrice, material, cor) }
    }

    suspend fun getProdutoById(id: String): ResultWrapper<Produto> {
        // TODO: Implementar mock para busca por ID se necessário, ou manter chamada da API
        return safeApiCall { apiService.getProdutoById(id) }
    }

    // ... (outros métodos, se houver)
}

