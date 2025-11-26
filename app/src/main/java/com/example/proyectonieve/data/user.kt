package com.example.proyectonieve.data

data class User(
    val correo: String,
    val passwordHash: String,
    val rut: String?,
    val rol: String
)