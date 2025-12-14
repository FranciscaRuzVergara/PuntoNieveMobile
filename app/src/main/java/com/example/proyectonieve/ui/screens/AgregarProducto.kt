package com.example.proyectonieve.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectonieve.data.Producto
import com.example.proyectonieve.sesion.SessionManager
import com.example.proyectonieve.ui.Routes
import com.example.proyectonieve.ui.components.ProductCard
import com.example.proyectonieve.ui.network.RetrofitInstance
import com.example.proyectonieve.ui.utils.validarCaracteres
import com.example.proyectonieve.ui.utils.validarNumero
import kotlinx.coroutines.launch

val Categorias = listOf("Torta", "Galleta", "Individual")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarProducto(navController: NavController) {

    val esAdmin = SessionManager.rolLogeado.value == "Admin"
    val esGerenteProductos = SessionManager.rolLogeado.value == "GerenteProductos"

    LaunchedEffect(Unit) {
        if (!esAdmin && !esGerenteProductos) {
            navController.navigate(Routes.Home) {
                popUpTo(Routes.Home) { inclusive = true }
            }
        }
    }

    if (!esAdmin && !esGerenteProductos) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No autorizado")
        }
        return
    }

    val scope = rememberCoroutineScope()

    var precio by remember { mutableStateOf("") }
    var nombreProducto by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagen by remember { mutableStateOf("") }

    var categoria by remember { mutableStateOf(Categorias.first()) }
    var isExpanded by remember { mutableStateOf(false) }

    var nombreError by remember { mutableStateOf(false) }
    var precioError by remember { mutableStateOf(false) }

    val isFormValid = !nombreError && nombreProducto.isNotBlank() &&
            precio.isNotBlank() && !precioError


    var productoEditando by remember { mutableStateOf<Producto?>(null) }

    var productos by remember { mutableStateOf<List<Producto>>(emptyList()) }
    var isLoadingList by remember { mutableStateOf(false) }
    var listError by remember { mutableStateOf<String?>(null) }

    var isSaving by remember { mutableStateOf(false) }
    var postError by remember { mutableStateOf<String?>(null) }

    fun limpiarFormulario() {
        precio = ""
        nombreProducto = ""
        descripcion = ""
        imagen = ""
        categoria = Categorias.first()
        nombreError = false
        precioError = false
        productoEditando = null
    }

    fun cargarProductos() {
        isLoadingList = true
        listError = null
        scope.launch {
            try {
                productos = RetrofitInstance.productoApi.listarProductos()
            } catch (e: Exception) {
                listError = "Error cargando productos: ${e.message}"
            } finally {
                isLoadingList = false
            }
        }
    }

    LaunchedEffect(Unit) { cargarProductos() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (esGerenteProductos) {
            item {
                Text(
                    text = if (productoEditando == null) "Agregar Producto" else "Editar Producto",
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
                    modifier = Modifier.fillMaxWidth()
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
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripcion del producto") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = imagen,
                    onValueChange = { imagen = it },
                    label = { Text("URL de la imagen") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = !isExpanded },
                    modifier = Modifier.fillMaxWidth()
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

                if (postError != null) {
                    Text(text = postError!!, color = MaterialTheme.colorScheme.error)
                    Spacer(Modifier.height(8.dp))
                }

                Button(
                    onClick = {
                        postError = null
                        isSaving = true

                        scope.launch {
                            try {
                                val baseProducto = Producto(
                                    idProducto = productoEditando?.idProducto,
                                    precio = precio.toIntOrNull() ?: 0,
                                    nombreProducto = nombreProducto.trim(),
                                    descripcion = descripcion.trim(),
                                    imagen = imagen.trim(),
                                    categoria = categoria.trim()
                                )

                                if (productoEditando?.idProducto != null) {
                                    val id = productoEditando!!.idProducto!!
                                    RetrofitInstance.productoApi.actualizarProducto(id, baseProducto)
                                } else {
                                    RetrofitInstance.productoApi.registrarProducto(baseProducto)
                                }

                                limpiarFormulario()
                                cargarProductos()
                            } catch (e: Exception) {
                                postError = "Error al guardar: ${e.message}"
                            } finally {
                                isSaving = false
                            }
                        }
                    },
                    enabled = isFormValid && !isSaving,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = if (productoEditando == null) "Agregar Producto" else "Guardar cambios")
                }

                if (productoEditando != null) {
                    Spacer(Modifier.height(10.dp))
                    Button(
                        onClick = { limpiarFormulario() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(25.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray,
                            contentColor = Color.Black
                        )
                    ) {
                        Text("Cancelar edición")
                    }
                }

                Spacer(Modifier.height(20.dp))
                Divider()
                Spacer(Modifier.height(14.dp))
            }
        }

        item {
            Text(
                text = "Lista de productos",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(10.dp))

            if (isLoadingList) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
                Spacer(Modifier.height(10.dp))
            }

            if (listError != null) {
                Text(text = listError!!, color = MaterialTheme.colorScheme.error)
                Spacer(Modifier.height(10.dp))
            }
        }

        items(productos) { product ->
            ProductCard(
                product = product,
                puedeEditar = esGerenteProductos,
                puedeEliminar = esAdmin,
                onEdit = { p ->
                    productoEditando = p
                    nombreProducto = p.nombreProducto
                    precio = p.precio.toString()
                    descripcion = p.descripcion ?: ""
                    imagen = p.imagen ?: ""
                    categoria = p.categoria ?: Categorias.first()

                    nombreError = !validarCaracteres(nombreProducto)
                    precioError = precio.isNotBlank() && !validarNumero(precio)
                },
                onDeleted = {
                    cargarProductos()
                }
            )
        }

        item { Spacer(Modifier.height(30.dp)) }
    }
}
