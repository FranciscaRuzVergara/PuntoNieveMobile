package com.example.proyectonieve

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectonieve.ui.components.Menu
import com.example.proyectonieve.ui.screens.Home
import com.example.proyectonieve.ui.theme.ProyectoNieveTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoNieveTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar={
                        CenterAlignedTopAppBar(
                            title={
                                Text(text="Punto Nieve",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis)
                                },
                            actions= {
                                    Menu()
                            }
                        )
                    }
                ){ innerPadding ->
                    Home(
                        modifier=Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
