package com.example.proyectonieve.data



data class Producto(
    val idProducto: Long?,
    val precio: Int,
    val nombreProducto: String,
    val descripcion: String?,
    val imagen: String?,
    val categoria: String?
)
