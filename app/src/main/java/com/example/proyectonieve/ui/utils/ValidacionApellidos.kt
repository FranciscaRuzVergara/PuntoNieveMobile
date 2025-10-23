package com.example.proyectonieve.ui.utils

fun validarApellidos(apellidos: String): Boolean {
    val regex = "^[a-zA-ZÁÉÍÓÚáéíóúÑñ]+( [a-zA-ZÁÉÍÓÚáéíóúÑñ]+)?$".toRegex()
    return regex.matches(apellidos.trim())
}

