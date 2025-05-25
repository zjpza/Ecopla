package com.example.projetonovoecopla.data.network

import com.example.projetonovoecopla.data.models.AuthRequest // Placeholder - needs creation
import com.example.projetonovoecopla.data.models.AuthResponse // Placeholder - needs creation
import com.example.projetonovoecopla.data.models.Cart // Placeholder - needs creation
import com.example.projetonovoecopla.data.models.CartItem // Placeholder - needs creation
import com.example.projetonovoecopla.data.models.Pedido // Placeholder - needs creation
import com.example.projetonovoecopla.data.models.Produto // Placeholder - needs creation
import com.example.projetonovoecopla.data.models.SignupRequest // Placeholder - needs creation
import com.example.projetonovoecopla.data.models.Usuario // Placeholder - needs creation
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // Auth
    @POST("/api/auth/signup")
    suspend fun signup(@Body request: SignupRequest): Response<Usuario>

    @POST("/api/auth/login")
    suspend fun login(@Body request: AuthRequest): Response<AuthResponse>

    // Produtos
    @GET("/api/produtos")
    suspend fun getProdutos(): Response<List<Produto>>

    @GET("/api/produtos/search")
    suspend fun searchProdutos(
        @Query("nome") nome: String? = null,
        @Query("minPrice") minPrice: Double? = null,
        @Query("maxPrice") maxPrice: Double? = null,
        @Query("material") material: String? = null,
        @Query("cor") cor: String? = null
    ): Response<List<Produto>>

    @GET("/api/produtos/{id}")
    suspend fun getProdutoById(@Path("id") id: String): Response<Produto>

    // Cart
    @GET("/api/cart")
    suspend fun getCart(): Response<Cart>

    @POST("/api/cart/items")
    suspend fun addItemToCart(@Body item: CartItem): Response<Cart>

    @PUT("/api/cart/items")
    suspend fun updateCartItem(@Body item: CartItem): Response<Cart>

    @DELETE("/api/cart/items/{productId}")
    suspend fun removeCartItem(@Path("productId") productId: String): Response<Cart>

    @DELETE("/api/cart")
    suspend fun clearCart(): Response<Void>

    // Pedidos
    @POST("/api/pedidos")
    suspend fun createPedido(): Response<Pedido>

    @GET("/api/pedidos/mine")
    suspend fun getMyPedidos(): Response<List<Pedido>>

    @GET("/api/pedidos/{id}")
    suspend fun getPedidoById(@Path("id") id: String): Response<Pedido>

    @GET("/api/usuarios/{id}")
    suspend fun getUsuario(@Path("id") id: String): Response<Usuario>

    @PUT("/api/usuarios/{id}")
    suspend fun updateUsuario(@Path("id") id: String, @Body usuario: Usuario): Response<Usuario>

    // Note: File upload for Encomenda/Venda needs clarification on API endpoint and structure.
    // If the API doesn't support it, this part will be mocked in the app.
}
