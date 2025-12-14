package com.example.proyectonieve.ui.network
import com.example.proyectonieve.data.Producto
import retrofit2.Response
import retrofit2.http.*

interface ProductoApi {

        @GET("api/producto")
        suspend fun listarProductos(): List<Producto>

        @GET("api/producto/{id}")
        suspend fun buscarProducto(@Path("id") id: Long): Producto

        @POST("api/producto")
        suspend fun registrarProducto(@Body producto: Producto): Producto

        @PUT("api/producto/{id}")
        suspend fun actualizarProducto(@Path("id") id: Long, @Body producto: Producto): Producto

        @DELETE("/api/producto/{id}")
        suspend fun eliminarProducto(@Path("id") id: Long): Response<Unit>
    }
