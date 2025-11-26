package com.example.proyectonieve.model

data class Product(
    val idProducto: Long?,
    val precio: Int,
    val nombreProducto: String,
    val descripcion: String?,
    val imagen: String?,
    val categoria: String?
)