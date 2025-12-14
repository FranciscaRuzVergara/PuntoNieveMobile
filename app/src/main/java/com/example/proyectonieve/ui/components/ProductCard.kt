package com.example.proyectonieve.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.proyectonieve.data.Producto
import com.example.proyectonieve.ui.network.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Response // ✅ NUEVO

@Composable
fun ProductCard(
    product: Producto,
    puedeEditar: Boolean = false,
    puedeEliminar: Boolean = false,
    onEdit: (Producto) -> Unit = {},
    onDeleted: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()

    var showDeleteDialog by remember { mutableStateOf(false) }
    var isDeleting by remember { mutableStateOf(false) }
    var deleteError by remember { mutableStateOf<String?>(null) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Row(modifier = Modifier.fillMaxWidth()) {

                AsyncImage(
                    model = product.imagen,
                    contentDescription = "Imagen de ${product.nombreProducto}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 8.dp)
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 12.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = product.nombreProducto,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = product.descripcion ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Gray
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "$${product.precio}",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }
            }

            if (puedeEditar || puedeEliminar) {

                if (deleteError != null) {
                    Text(
                        text = deleteError!!,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    if (puedeEditar) {
                        OutlinedButton(
                            onClick = { onEdit(product) },
                            enabled = !isDeleting
                        ) {
                            Icon(Icons.Outlined.Edit, contentDescription = "Editar")
                            Spacer(Modifier.width(8.dp))
                            Text("Editar")
                        }
                    }

                    if (puedeEliminar) {
                        Spacer(Modifier.width(10.dp))

                        Button(
                            onClick = { showDeleteDialog = true },
                            enabled = !isDeleting,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error,
                                contentColor = MaterialTheme.colorScheme.onError
                            )
                        ) {
                            Icon(Icons.Outlined.Delete, contentDescription = "Eliminar")
                            Spacer(Modifier.width(8.dp))
                            Text(if (isDeleting) "Eliminando..." else "Eliminar")
                        }
                    }
                }
            }
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = {
                if (!isDeleting) showDeleteDialog = false
            },
            title = { Text("Eliminar producto") },
            text = { Text("¿Seguro que quieres eliminar “${product.nombreProducto}”?") },
            confirmButton = {
                Button(
                    onClick = {
                        val id = product.idProducto
                        if (id == null) {
                            deleteError = "No se pudo eliminar: idProducto nulo."
                            showDeleteDialog = false
                            return@Button
                        }

                        isDeleting = true
                        deleteError = null

                        scope.launch {
                            try {
                                val res = RetrofitInstance.productoApi.eliminarProducto(id)
                                if (!res.isSuccessful) {
                                    throw Exception("HTTP ${res.code()}")
                                }

                                showDeleteDialog = false
                                onDeleted()
                            } catch (e: Exception) {
                                deleteError = "Error al eliminar: ${e.message}"
                            } finally {
                                isDeleting = false
                            }
                        }
                    },
                    enabled = !isDeleting
                ) { Text("Sí, eliminar") }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { showDeleteDialog = false },
                    enabled = !isDeleting
                ) { Text("Cancelar") }
            }
        )
    }
}
