package com.example.proyectonieve.ui.network

import com.example.proyectonieve.data.Carrito;
import java.util.List;
import retrofit2.http.*;
interface CarritoApi {


    interface CarritoApi {
        @GET("api/carrito")
        suspend fun listarCarritos(): List<Carrito>

        @GET("api/carrito/{nroPedido}/{idProducto}")
        suspend fun buscarCarrito(
            @Path("nroPedido") nroPedido: Long,
            @Path("idProducto") idProducto: Long
        ): Carrito

        @POST("api/carrito")
        suspend fun registrarCarrito(@Body carrito: Carrito): Carrito

        @PUT("api/carrito/{nroPedido}/{idProducto}")
        suspend fun actualizarCarrito(
            @Path("nroPedido") nroPedido: Long,
            @Path("idProducto") idProducto: Long,
            @Body carrito: Carrito
        ): Carrito

        @DELETE("api/carrito/{nroPedido}/{idProducto}")
        suspend fun eliminarCarrito(
            @Path("nroPedido") nroPedido: Long,
            @Path("idProducto") idProducto: Long
        )


    }

}