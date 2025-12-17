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

    suspend fun iniciarSesionAsync(email: String, rol: String) {
        kotlinx.coroutines.delay(1500) // Simula espera de red de 1.5 segundos
        correoLogeado.value = email
        rolLogeado.value = rol
    }


        fun esAdmin(): Boolean = rolLogeado.value == "Admin"
        fun esGerenteProductos(): Boolean = rolLogeado.value == "GerenteProductos"

        fun esSuperAdmin(): Boolean = rolLogeado.value == "SuperAdmin"



        fun puedeCrearEditarProducto(): Boolean = esGerenteProductos()
        fun puedeEliminarProducto(): Boolean = esAdmin()
    }

