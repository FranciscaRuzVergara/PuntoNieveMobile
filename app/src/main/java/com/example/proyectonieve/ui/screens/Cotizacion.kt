package com.example.proyectonieve.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectonieve.ui.Routes
import com.example.proyectonieve.ui.utils.validarCaracteres
import com.example.proyectonieve.ui.utils.validarCorreo
import com.example.proyectonieve.ui.utils.validarNumero

val SaboresBizcocho = listOf("Vainilla", "Chocolate", "Red Velvet", "Limón")

val OpcionesRelleno = listOf("Manjar", "Crema Pastelera", "Chocolate Blanco", "Nuez")

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun Cotizacion(navController: NavController) {
    var nombreCliente by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var numeroPersonas by remember { mutableStateOf("") }
    var masDetalles by remember { mutableStateOf("") }

    var saborBizcocho by remember { mutableStateOf(SaboresBizcocho.first()) }
    var rellenosSeleccionados by remember { mutableStateOf(setOf<String>()) }
    var isExpanded by remember { mutableStateOf(false) }

    var nombreError by remember { mutableStateOf(false) }
    var correoError by remember { mutableStateOf(false) }
    var personasError by remember { mutableStateOf(false) }

    val isFormValid = !nombreError && nombreCliente.isNotBlank() &&
            !correoError && correo.isNotBlank() &&
            !personasError && numeroPersonas.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cotización",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = nombreCliente,
            onValueChange = {
                nombreCliente = it
                nombreError = !validarCaracteres(it)
            },
            label = { Text("Nombre Cliente") },
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
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = numeroPersonas,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                    numeroPersonas = newValue
                    personasError = newValue.isNotBlank() && !validarNumero(newValue)
                }
            },
            label = { Text("Número de Personas (Aprox.)") },
            isError = personasError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            supportingText = {
                if (personasError) {
                    Text("Debe ser un número mayor a cero.", color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = saborBizcocho,
                onValueChange = {},
                readOnly = true,
                label = { Text("Sabor de Bizcocho") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                SaboresBizcocho.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            saborBizcocho = selectionOption
                            isExpanded = false
                        }
                    )
                }
            }
        }
        Spacer(Modifier.height(16.dp))

        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Text("Seleccione Relleno(s):", style = MaterialTheme.typography.titleMedium)

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OpcionesRelleno.forEach { relleno ->
                    FilterChip(
                        selected = relleno in rellenosSeleccionados,
                        onClick = {
                            rellenosSeleccionados = if (relleno in rellenosSeleccionados) {
                                rellenosSeleccionados - relleno
                            } else {
                                rellenosSeleccionados + relleno
                            }
                        },
                        label = { Text(relleno) }
                    )
                }
            }
        }
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = masDetalles,
            onValueChange = { masDetalles = it },
            label = { Text("Más Detalles (Opcional)") },
            minLines = 3,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )

        Spacer(Modifier.height(30.dp))

        Button(
            onClick = {
                navController.navigate(Routes.Home)
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
                text = "Solicitar Cotización"
            )
        }
    }
}