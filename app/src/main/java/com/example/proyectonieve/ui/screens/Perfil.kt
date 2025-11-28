package com.example.proyectonieve.ui.screens

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.remember
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectonieve.ui.utils.validarCaracteres
import com.example.proyectonieve.ui.utils.validarApellidos
import com.example.proyectonieve.ui.utils.validarCorreo
import com.example.proyectonieve.ui.utils.validarRut
import com.example.proyectonieve.data.User
import com.example.proyectonieve.sesion.SessionManager
import com.example.proyectonieve.ui.Routes
import com.example.proyectonieve.ui.network.RetrofitInstance
import kotlinx.coroutines.launch

@Composable
fun PerfilScreen(
    navController: NavController,
    user: User? = null
) {
    var nombres by remember { mutableStateOf(user?.nombres ?: "") }
    var apellidos by remember { mutableStateOf(user?.apellidos ?: "") }
    var correo by remember { mutableStateOf(user?.correo ?: "") }
    var rut by remember { mutableStateOf(user?.rut ?: "") }
    var password by remember { mutableStateOf("") }
    var rol by remember { mutableStateOf(user?.rol ?: "") }

    var nombresError by remember { mutableStateOf(false) }
    var apellidosError by remember { mutableStateOf(false) }
    var correoError by remember { mutableStateOf(false) }
    var rutError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.White, Color.LightGray)
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(40.dp))

        Column {

            OutlinedTextField(
                value = nombres,
                onValueChange = {
                    nombres = it
                    nombresError = !validarCaracteres(it)
                },
                label = { Text("Nombres") },
                isError = nombresError,
                supportingText = {
                    if (nombresError) {
                        Text("Solo letras y espacios.", color = MaterialTheme.colorScheme.error)
                    }
                },
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
                isError = apellidosError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
            )
            if (apellidosError) {
                Text(
                    text = "Solo letras. Ej: Felipe Cañete",
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 35.dp)
                )
            }

            Spacer(Modifier.height(25.dp))

            OutlinedTextField(
                value = rut,
                onValueChange = { input ->
                    val limpio = input.filter { it.isDigit() || it == 'k' || it == 'K' || it == '-' }
                        .take(10)

                    rut = limpio
                    rutError = !validarRut(limpio)
                },
                label = { Text("RUT") },
                isError = rutError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
                value = correo,
                onValueChange = {
                    correo = it
                    correoError = it.isNotBlank() && !validarCorreo(it)
                },
                label = { Text("Correo Electrónico") },
                isError = correoError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                supportingText = {
                    if (correoError) {
                        Text("Formato de correo inválido.", color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
            )

            Spacer(Modifier.height(25.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = it.isNotEmpty() && it.length < 6
                },
                label = { Text("Nueva contraseña (opcional)") },
                isError = passwordError,
                visualTransformation = PasswordVisualTransformation(),
                supportingText = {
                    if (passwordError) {
                        Text("Mínimo 6 caracteres.", color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
            )

            Spacer(Modifier.height(25.dp))

            OutlinedTextField(
                value = rol,
                onValueChange = {},
                label = { Text("Rol") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
            )

            Spacer(Modifier.height(30.dp))

            Button(
                onClick = {
                    if (user != null && user.id != null &&
                        !nombresError && !apellidosError && !correoError && !rutError && !passwordError
                    ) {
                        val updatedUser = user.copy(
                            nombres = nombres,
                            apellidos = apellidos,
                            correo = correo,
                            rut = rut,
                            rol = rol,
                            passwordHash = if (password.isNotEmpty()) password else user.passwordHash
                        )

                        scope.launch {
                            try {
                                val response = RetrofitInstance.userApi.actualizarUsuario(updatedUser.id!!, updatedUser)

                                SessionManager.usuarioActual = response
                                SessionManager.correoLogeado.value = response.correo
                                SessionManager.rolLogeado.value = response.rol

                                // Feedback visual o navegación
                                navController.navigate(Routes.Home)
                            } catch (e: Exception) {
                                println("Error al actualizar: ${e.message}")
                            }
                        }
                    }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(25.dp),
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
