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
import com.example.proyectonieve.ui.utils.validarApellidos
import com.example.proyectonieve.ui.utils.validarDireccion
import com.example.proyectonieve.ui.utils.validarEdad
import com.example.proyectonieve.ui.utils.validarRut


@Composable
fun FormularioScreen(modifier: Modifier = Modifier) {

    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var rut by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var direccion by remember {mutableStateOf("")}
    var correo by remember { mutableStateOf("") }
    var click by remember { mutableStateOf("") }
    //Validaciones jijis
    var rutError by remember { mutableStateOf(false) }
    var edadError by remember { mutableStateOf(false)}
    var apellidosError by remember {mutableStateOf(false)}
    var direccionError by remember {mutableStateOf(false)}


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
            text = "Punto Nieve",
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

        Spacer(Modifier.height(25.dp))

        Column {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
            )

            Spacer(Modifier.height(25.dp))

            OutlinedTextField(
                value = apellidos,
                onValueChange = {
                    apellidos = it
                    apellidosError = !validarApellidos(it)
                },
                label = { Text("Apellidos") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
            )
            if (apellidosError) {
                Text(
                    text = "solo letras.  EJ: Felipe Cañete",
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 35.dp)
                )
            }


            Spacer(Modifier.height(25.dp))

            OutlinedTextField(
                value = rut,
                onValueChange = {
                    rut = it
                    rutError = !validarRut(it)
                },
                label = { Text("RUT") },
                isError = rutError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
            )
            if (rutError) {
                Text(
                    text = "RUT inválido. Ej: 12345678-9",
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 35.dp)
                )
            }



            Spacer(Modifier.height(25.dp))

            OutlinedTextField(
                value = edad,
                onValueChange = {
                    edad = it
                    edadError = !validarEdad(it.toIntOrNull() ?: -1)
                },
                label = { Text("Edad") },
                isError = edadError,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 35.dp)
            )
            if (edadError) {
                Text(
                    text = "edad no valida, no se puede exedir de los 110 años o usar caracteres/letras",
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 35.dp)
                )
            }

            Spacer(Modifier.height(25.dp))

            OutlinedTextField(
                value = direccion,
                onValueChange = {
                    direccion = it
                    direccionError = !validarDireccion(it)
                },
                label = { Text("Dirección") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
            )
            if (direccionError) {
                Text(
                    text = "direccion no valida",
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 35.dp)
                )
            }




            Spacer(Modifier.height(30.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
            )

            Spacer(Modifier.height(25.dp))

            OutlinedTextField(
                value = click,
                onValueChange = { click = it },
                label = { Text("Check") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
            )

            Spacer(Modifier.height(25.dp))


            Button(
                onClick = {

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
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "GUARDAR",
                    style = TextStyle(fontSize = 21.sp)
                )
            }
        }
    }
}
@Preview(showBackground = true, name = "Formulario Preview")
@Composable
fun FormularioScreenPreview() {
    FormularioScreen()
}

