package com.example.proyectonieve.sesion



import androidx.compose.runtime.mutableStateOf

object SessionManager {
    var correoLogeado = mutableStateOf<String?>(null)
    var rolLogeado = mutableStateOf<String?>(null)

    val clientes = listOf(
        "fran@nieve.cl",
        "felipe@nieve.cl"
    )

    fun esCliente(): Boolean {
        return rolLogeado.value == "Cliente" &&
                clientes.contains(correoLogeado.value)
    }
}
