package com.example.proyectonieve.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectonieve.ui.Routes
import com.example.proyectonieve.sesion.SessionManager

@Composable
fun LoginScreen(navController: NavController, modifier: Modifier = Modifier) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.White, Color.LightGray)
                )
            )
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(40.dp))

        Text(
            text = "Iniciar sesión",
            style = TextStyle(
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraBold,
                shadow = Shadow(
                    color = Color.Gray,
                    offset = Offset(1f, 1f),
                    blurRadius = 2f
                )
            )
        )

        Spacer(Modifier.height(30.dp))

        Column {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
            )

            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
            )

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    if (email.isNotBlank() && password.isNotBlank()) {

                        SessionManager.correoLogeado.value = email

                        SessionManager.rolLogeado.value =
                            if (SessionManager.clientes.contains(email))
                                "Cliente"
                            else
                                "Admin"

                        if (SessionManager.esCliente()) {
                            navController.navigate(Routes.Home)
                        } else {
                            navController.navigate(Routes.Home)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(25.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    Color.DarkGray,
                    Color.White
                )
            ) {
                Text(
                    text = "Ingresar",
                    style = TextStyle(fontSize = 21.sp)
                )
            }

            Spacer(Modifier.height(10.dp))

            TextButton(
                onClick = {},
                modifier = Modifier.padding(horizontal = 35.dp)
            ) {
                Text("¿No tienes cuenta? Regístrate")
            }
        }
    }
}

@Preview(showBackground = true, name = "Login Preview")
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}
