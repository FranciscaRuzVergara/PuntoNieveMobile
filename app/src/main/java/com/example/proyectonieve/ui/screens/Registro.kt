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
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rut by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") })
        TextField(value = rut, onValueChange = { rut = it }, label = { Text("RUT") })

        Button(onClick = {
            scope.launch {
                try {
                    val us = User(correo, password, rut, "USUARIO")
                    val response = RetrofitInstance.userApi.registerUser(us)
                    result = "Usuario creado: ${response.correo}"
                } catch (e: Exception) {
                    result = "Error: ${e.message}"
                }
            }
        }) {
            Text("Registrar usuario")
        }

        Text(result)
    }
}
