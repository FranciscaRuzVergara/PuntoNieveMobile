package com.example.proyectonieve.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.proyectonieve.sesion.SessionManager
import com.example.proyectonieve.ui.Routes
import kotlinx.coroutines.launch

@Composable
fun Menu(navController: NavController, snackbarHostState: SnackbarHostState) {
    var expanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val esAdmin = SessionManager.rolLogeado.value == "Admin"
    val esGerenteProductos = SessionManager.rolLogeado.value == "GerenteProductos"
    val esSuperAdmin = SessionManager.rolLogeado.value == "SuperAdmin"

    val esCliente = !esAdmin && !esGerenteProductos

    Box(
        modifier = Modifier,
        contentAlignment = Alignment.TopEnd
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            //TODOS

            DropdownMenuItem(
                text = { Text("Perfil") },
                leadingIcon = { Icon(Icons.Outlined.Person, null) },
                onClick = {
                    expanded = false
                    navController.navigate(Routes.Perfil)
                }
            )

            DropdownMenuItem(
                text = { Text("Home") },
                leadingIcon = { Icon(Icons.Outlined.Home, null) },
                onClick = {
                    expanded = false
                    scope.launch {
                        snackbarHostState.showSnackbar("Dirigiéndose al Home")
                        navController.navigate(Routes.Home)
                    }
                }
            )

            DropdownMenuItem(
                text = { Text("Cámara") },
                leadingIcon = { Icon(Icons.Outlined.Create, null) },
                onClick = {
                    expanded = false
                    navController.navigate(Routes.Camara)
                }
            )

            //ADMIN Y SUPERADMIN
            if (esAdmin || esSuperAdmin) {
                DropdownMenuItem(
                    text = { Text("Productos") },
                    leadingIcon = { Icon(Icons.Outlined.ShoppingCart, null) },
                    onClick = {
                        expanded = false
                        navController.navigate(Routes.AgregarProducto)
                    }
                )
            }

            //GERENTE DE PRODUCTOS Y SUPER ADMIN
            if (esGerenteProductos || esSuperAdmin) {
                if (!esSuperAdmin) {
                    DropdownMenuItem(
                        text = { Text("Productos") },
                        leadingIcon = { Icon(Icons.Outlined.ShoppingCart, null) },
                        onClick = {
                            expanded = false
                            navController.navigate(Routes.AgregarProducto)
                        }
                    )
                }

                DropdownMenuItem(
                    text = { Text("Misión") },
                    leadingIcon = { Icon(Icons.Outlined.Info, null) },
                    onClick = {
                        expanded = false
                        navController.navigate(Routes.Mision)
                    }
                )
            }

            //CLIENTE y SUPERADMIN
            if (esCliente || esSuperAdmin) {
                if (!esGerenteProductos && !esSuperAdmin) {
                    DropdownMenuItem(
                        text = { Text("Misión") },
                        leadingIcon = { Icon(Icons.Outlined.Info, null) },
                        onClick = {
                            expanded = false
                            navController.navigate(Routes.Mision)
                        }
                    )
                }

                DropdownMenuItem(
                    text = { Text("Beneficios") },
                    leadingIcon = { Icon(Icons.Outlined.Star, null) },
                    onClick = {
                        expanded = false
                        navController.navigate(Routes.Formulario)
                    }
                )

                DropdownMenuItem(
                    text = { Text("Cotización") },
                    leadingIcon = { Icon(Icons.Outlined.Create, null) },
                    onClick = {
                        expanded = false
                        navController.navigate(Routes.Cotizacion)
                    }
                )
            }
        }
    }
}


/********
            DropdownMenuItem(
                text = { Text("Pedidos") },
                leadingIcon = { Icon(Icons.Outlined.MailOutline, contentDescription = null) },
                onClick = { /* Do something... */ }
            )
            DropdownMenuItem(
                text = { Text("Carrito de compras") },
                leadingIcon = { Icon(Icons.Outlined.ShoppingCart, contentDescription = null) },
                onClick = {/* do something...*/}
            )

            HorizontalDivider()

            DropdownMenuItem(
                text = { Text("Ajustes") },
                leadingIcon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
                onClick = { /* Do something... */ }
            )
            ***/



