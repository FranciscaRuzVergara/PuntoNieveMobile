package com.example.proyectonieve.sesion



import androidx.compose.runtime.mutableStateOf
import com.example.proyectonieve.data.User

object SessionManager {
    var usuarioActual: User? = null

    var correoLogeado = mutableStateOf<String?>(null)
    var rolLogeado = mutableStateOf<String?>(null)


    var Admins = listOf(
        "fran@nieve.cl",
        "felipe@nieve.cl"
    )


        fun esAdmin(): Boolean = rolLogeado.value == "Admin"
        fun esGerenteProductos(): Boolean = rolLogeado.value == "GerenteProductos"


        fun puedeCrearEditarProducto(): Boolean = esGerenteProductos()
        fun puedeEliminarProducto(): Boolean = esAdmin()
    }

