package com.example.proyectonieve.ui.utils


fun validarRut(rut: String): Boolean {
    val regex = Regex("^\\d{7,8}-[\\dkK]\$")
    return rut.length == 10 && regex.matches(rut)
}
