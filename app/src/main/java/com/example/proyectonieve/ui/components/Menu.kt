package com.example.proyectonieve.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.proyectonieve.sesion.SessionManager
import com.example.proyectonieve.ui.Routes
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch


@Composable
fun Menu(navController: NavController,snackbarHostState: SnackbarHostState) {
    var expanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

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

            DropdownMenuItem(
                text = { Text("Perfil") },
                leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = null) },
                onClick = {
                    expanded = false
                    navController.navigate(Routes.Perfil)
                }
            )
            DropdownMenuItem(
                text = { Text("Home") },
                leadingIcon = { Icon(Icons.Outlined.Home, contentDescription = null) },
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
                leadingIcon = { Icon(Icons.Outlined.Create, contentDescription = null) },
                onClick = {
                    expanded = false
                    navController.navigate(Routes.Camara)
                }
            )

            if (SessionManager.esAdmin()) {

                DropdownMenuItem(
                    text = { Text("Productos") },
                    leadingIcon = { Icon(Icons.Outlined.ShoppingCart, contentDescription = null) },
                    onClick = {
                        expanded = false
                        navController.navigate(Routes.AgregarProducto)
                    }
                )
            }


            else {
                DropdownMenuItem(
                    text = { Text("Misión") },
                    leadingIcon = { Icon(Icons.Outlined.Info, contentDescription = null) },
                    onClick = {
                        expanded = false
                        navController.navigate(Routes.Mision)
                    }
                )

                DropdownMenuItem(
                    text = { Text("Beneficios") },
                    leadingIcon = { Icon(Icons.Outlined.Star, contentDescription = null) },
                    onClick = {
                        expanded = false
                        navController.navigate(Routes.Formulario)
                    }
                )

                DropdownMenuItem(
                    text = { Text("Cotización") },
                    leadingIcon = { Icon(Icons.Outlined.Create, contentDescription = null) },
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



