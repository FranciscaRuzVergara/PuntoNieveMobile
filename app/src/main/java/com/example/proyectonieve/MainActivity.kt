package com.example.proyectonieve

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectonieve.ui.components.Menu
import com.example.proyectonieve.ui.screens.FormularioScreen
import com.example.proyectonieve.ui.screens.Home
import com.example.proyectonieve.ui.theme.ProyectoNieveTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectonieve.ui.Routes
import com.example.proyectonieve.ui.Routes.Camara
import com.example.proyectonieve.ui.Routes.Login
import com.example.proyectonieve.ui.screens.CameraScreen
import com.example.proyectonieve.ui.screens.Cotizacion
import com.example.proyectonieve.ui.screens.Mision
import com.example.proyectonieve.ui.screens.LoginScreen


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoNieveTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                //titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(
                                    text = "Punto Nieve",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            actions = {
                                Menu(navController)
                            }
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Login,
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable(Routes.Login) {
                            LoginScreen(navController)
                        }
                        composable(Routes.Home,){
                            Home(navController)
                        }
                        composable( Routes.Mision, ) {
                                Mision(navController)
                            }
                        composable( Routes.Formulario, ) {
                            FormularioScreen(navController)
                            }
                        composable( Routes.Cotizacion, ) {
                            Cotizacion(navController)
                            }
                        composable(Routes.Camara) {
                            CameraScreen()
                        }

                        }
                    }
                }
            }
        }
    }

