package com.example.proyectonieve.data

data class User(
    val id: Long? = null,
    val correo: String,
    val passwordHash: String? = null,
    val nombres: String,
    val apellidos: String,
    val rol: String,
    val rut: String? = null,
    val fechaRegistro: String? = null
)