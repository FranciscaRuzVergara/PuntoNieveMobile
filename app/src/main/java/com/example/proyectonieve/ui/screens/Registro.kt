package com.example.proyectonieve.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyectonieve.data.User
import com.example.proyectonieve.ui.network.RetrofitInstance
import kotlinx.coroutines.launch

@Composable
fun Registro() {
    val scope = rememberCoroutineScope()

    var nombres by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rut by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        TextField(value = nombres, onValueChange = { nombres = it }, label = { Text("Nombres") })
        TextField(value = apellidos, onValueChange = { apellidos = it }, label = { Text("Apellidos") })
        TextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") })
        TextField(value = rut, onValueChange = { rut = it }, label = { Text("RUT") })

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            scope.launch {
                try {
                    val nuevoUsuario = User(
                        id = null,
                        correo = correo,
                        passwordHash = password,
                        nombres = nombres,
                        apellidos = apellidos,
                        rol = "Cliente",
                        rut = rut,
                        fechaRegistro = null
                    )

                    val response = RetrofitInstance.userApi.registerUser(nuevoUsuario)
                    result = "Usuario creado: ${response.correo}"

                } catch (e: Exception) {
                    result = "Error: ${e.message}"
                }
            }
        }) {
            Text("Registrar usuario")
        }

        Spacer(Modifier.height(16.dp))
        Text(result)
    }
}
