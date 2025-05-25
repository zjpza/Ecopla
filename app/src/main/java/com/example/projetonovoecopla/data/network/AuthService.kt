package com.example.projetonovoecopla.data.network

import com.example.projetonovoecopla.data.models.Cart
import com.example.projetonovoecopla.data.models.CartItem
import com.example.projetonovoecopla.data.models.LoginRequest
import com.example.projetonovoecopla.data.models.JwtResponse
import com.example.projetonovoecopla.data.models.Pedido
import com.example.projetonovoecopla.data.models.Produto
import com.example.projetonovoecopla.data.models.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthService {
    @POST("/api/auth/login")
    fun login(@Body request: LoginRequest): Call<JwtResponse>

    @GET("/api/cart")
    fun getCart(): Call<Cart>

    @POST("/api/cart/items")
    fun addItem(@Body item: CartItem): Call<Cart>

    @PUT("/api/cart/items")
    fun updateItem(@Body item: CartItem): Call<Cart>

    @DELETE("/api/cart/items/{productId}")
    fun removeItem(@Path("productId") productId: String): Call<Cart>

    @DELETE("/api/cart")
    fun clearCart(): Call<Void>

    @POST("/api/pedidos")
    fun criarPedido(): Call<Pedido>

    @GET("/api/pedidos/mine")
    fun listarMeusPedidos(): Call<List<Pedido>>

    @GET("/api/pedidos/{id}")
    fun getPedido(@Path("id") id: String): Call<Pedido>

    @GET("/api/pedidos/all")
    fun listarTodos(): Call<List<Pedido>>

    @GET("/api/produtos")
    fun listarProdutos(): Call<List<Produto>>

    @GET("/api/produtos/search")
    fun buscarProdutos(
        @Query("nome") nome: String?,
        @Query("minPrice") minPrice: Double?,
        @Query("maxPrice") maxPrice: Double?,
        @Query("material") material: String?,
        @Query("cor") cor: String?
    ): Call<List<Produto>>

    @GET("/api/produtos/{id}")
    fun buscarProdutoPorId(@Path("id") id: String): Call<Produto>

    @POST("/api/produtos")
    fun criarProduto(@Body produto: Produto): Call<Produto>

    @PUT("/api/produtos/{id}")
    fun atualizarProduto(@Path("id") id: String, @Body produto: Produto): Call<Produto>

    @DELETE("/api/produtos/{id}")
    fun deletarProduto(@Path("id") id: String): Call<Void>

    @GET("api/usuarios")
    fun listarUsuarios(): Call<List<Usuario>>

    @GET("api/usuarios/{id}")
    fun buscarUsuario(@Path("id") id: String): Call<Usuario>

    @PUT("api/usuarios/{id}")
    fun atualizarUsuario(@Path("id") id: String, @Body usuario: Usuario): Call<Usuario>

    @DELETE("api/usuarios/{id}")
    fun deletarUsuario(@Path("id") id: String): Call<Void>

    @GET("/api/usuarios/{id}")
    fun getUsuarioAtual(): Call<Usuario>
}