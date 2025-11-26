package com.example.proyectonieve.data


data class CarritoId(
    val nroPedido: Long,
    val idProducto: Long
)
data class Carrito(
    val id: CarritoId,
    val subtotal: Int,
    val cantidad: Int,
    val producto: Producto?,
    val pedido: Pedido?
)
