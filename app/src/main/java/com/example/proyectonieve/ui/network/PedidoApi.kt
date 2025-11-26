package com.example.proyectonieve.ui.network

import com.example.proyectonieve.data.Pedido
import retrofit2.http.*

interface PedidoApi {


    @GET("api/pedidos")
    suspend fun listarPedidos(): List<Pedido>

    @GET("api/pedidos/{id}")
    suspend fun buscarPedido(@Path("id") id: Long): Pedido

    @POST("api/pedidos")
    suspend fun registrarPedido(@Body pedido: Pedido): Pedido

    @PUT("api/pedidos/{id}")
    suspend fun actualizarPedido(@Path("id") id: Long, @Body pedido: Pedido): Pedido

    @DELETE("api/pedidos/{id}")
    suspend fun eliminarPedido(@Path("id") id: Long)
}
