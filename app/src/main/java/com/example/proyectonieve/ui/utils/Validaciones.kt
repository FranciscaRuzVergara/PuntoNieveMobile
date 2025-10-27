package com.example.proyectonieve.ui.utils

import android.util.Patterns

fun validarCaracteres(nombre: String): Boolean {
    if (nombre.isBlank()) return false
    return nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+\$".toRegex())
}

fun validarCorreo(correo: String): Boolean {
    if (correo.isBlank()) return false
    return Patterns.EMAIL_ADDRESS.matcher(correo).matches()
}

fun validarNumero(numStr: String): Boolean {
    if (numStr.isBlank()) return false
    return numStr.toIntOrNull()?.let { it > 0 } ?: false
}