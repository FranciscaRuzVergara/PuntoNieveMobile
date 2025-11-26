package com.example.proyectonieve.ui.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectonieve.ui.utils.validarApellidos
import com.example.proyectonieve.ui.utils.validarCaracteres
import com.example.proyectonieve.ui.utils.validarCorreo
import com.example.proyectonieve.ui.utils.validarDireccion
import com.example.proyectonieve.ui.utils.validarEdad
import com.example.proyectonieve.ui.utils.validarRut


@Composable
fun PerfilScreen(navController: NavController) {

    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var rut by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var direccion by remember {mutableStateOf("")}
    var correo by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }
    //Validaciones jijis
    var nombreError by remember { mutableStateOf(false) }
    var rutError by remember { mutableStateOf(false) }
    var edadError by remember { mutableStateOf(false)}
    var apellidosError by remember {mutableStateOf(false)}
    var direccionError by remember {mutableStateOf(false)}
    var correoError by remember { mutableStateOf(false) }


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

        /****
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
         ****/

        //Spacer(Modifier.height(25.dp))

        Column {

            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                    nombreError = !validarCaracteres(it)
                },
                label = { Text("Nombre Cliente") },
                isError = nombreError,
                supportingText = {
                    if (nombreError) {
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
                onValueChange = { input ->
                    val limpio = input.filter { it.isDigit() || it == 'k' || it == 'K'||it == '-' }
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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it }
                )
                Text(
                    "Acepto Terminos y condiciones"
                )
            }


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




