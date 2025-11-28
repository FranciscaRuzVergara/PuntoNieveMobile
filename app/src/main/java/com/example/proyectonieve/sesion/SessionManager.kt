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

    fun getCorreo(): String? = correoLogeado.value
    fun getRol(): String? = rolLogeado.value
    fun esAdmin(): Boolean {
        return rolLogeado.value == "Admin" &&
                Admins.contains(correoLogeado.value)
    }
}
