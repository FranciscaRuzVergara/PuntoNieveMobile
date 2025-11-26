package com.example.proyectonieve.data

import java.util.Date

data class Pedido (
    val nroPedido: Long?,
    val fechaPedido: Date,
    val user: User?,
    val carritos: List<Carrito>?
)