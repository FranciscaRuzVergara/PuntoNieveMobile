package com.example.proyectonieve.ui.utils

fun validarDireccion(direccion: String): Boolean {
    val regex = Regex("^[a-zA-ZÁÉÍÓÚáéíóúÑñ0-9 ,.#-]{5,100}$")
    return regex.matches(direccion.trim())
}