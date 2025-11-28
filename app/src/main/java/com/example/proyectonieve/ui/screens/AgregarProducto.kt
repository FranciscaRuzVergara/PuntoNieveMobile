package com.example.proyectonieve.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectonieve.data.Producto
import com.example.proyectonieve.ui.Routes
import com.example.proyectonieve.ui.network.RetrofitInstance
import com.example.proyectonieve.ui.utils.validarCaracteres
import com.example.proyectonieve.ui.utils.validarNumero
import kotlinx.coroutines.launch


val Categorias = listOf("Torta", "Galleta", "Individual")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarProducto(navController: NavController) {
    var precio by remember { mutableStateOf("") }
    var nombreProducto by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagen by remember { mutableStateOf("") }

    var categoria by remember { mutableStateOf(Categorias.first()) }
    var isExpanded by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    var postError by remember { mutableStateOf<String?>(null) }
    var postSuccess by remember { mutableStateOf(false) }

    var nombreError by remember { mutableStateOf(false) }
    var precioError by remember { mutableStateOf(false) }

    val isFormValid = !nombreError && nombreProducto.isNotBlank() &&
            precio.isNotBlank() && !precioError

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Agregar Producto",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = nombreProducto,
            onValueChange = {
                nombreProducto = it
                nombreError = !validarCaracteres(it)
            },
            label = { Text("Nombre Producto") },
            isError = nombreError,
            supportingText = {
                if (nombreError) {
                    Text("Solo letras y espacios.", color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = precio,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                    precio = newValue
                    precioError = newValue.isNotBlank() && !validarNumero(newValue)
                }
            },
            label = { Text("Precio del producto") },
            isError = precioError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            supportingText = {
                if (precioError) {
                    Text("Debe ser un número mayor a cero.", color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it},
            label = { Text("Descripcion del producto") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = imagen,
            onValueChange = { imagen = it },
            label = { Text("URL de la imagen") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )

        Spacer(Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = categoria,
                onValueChange = {},
                readOnly = true,
                label = { Text("Categoria") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                Categorias.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            categoria = selectionOption
                            isExpanded = false
                        }
                    )
                }
            }
        }
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                postError = null
                postSuccess = false
                isLoading = true
                scope.launch{
                    try {
                        val nuevoProducto = Producto(
                            null,
                            precio.toIntOrNull()?:0,
                            nombreProducto.trim(),
                            descripcion.trim(),
                            imagen.trim(),
                            categoria.trim()
                        )
                        val productoCreado = RetrofitInstance.productoApi.registrarProducto(nuevoProducto)
                        postSuccess = true
                        navController.navigate(Routes.Home)
                    }catch (e:Exception){
                        postError = "Error al guardar: ${e.message}"
                        println("Error al registrar producto: $e")
                    }finally {
                        isLoading = false
                    }
                }

            },
            enabled = isFormValid,
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
                text = "Agregar Producto"
            )
        }
    }
}