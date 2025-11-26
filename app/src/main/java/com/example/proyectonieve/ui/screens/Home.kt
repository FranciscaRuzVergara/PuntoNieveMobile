package com.example.proyectonieve.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.navigation.NavController
import com.example.proyectonieve.ui.components.ProductCard
import com.example.proyectonieve.data.Producto
import com.example.proyectonieve.ui.network.RetrofitInstance
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.lazy.items




@Composable
fun Home(navController: NavController) {

    var products by remember { mutableStateOf<List<Producto>>(emptyList()) }


    var error by remember { mutableStateOf<String?>(null) }


    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                products = RetrofitInstance.productoApi.listarProductos()
            } catch (e: Exception) {
                error = "Error cargando productos: ${e.message}"
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.White, Color.White)
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(Modifier.height(20.dp))
            AsyncImage(
            model = "https://thumbs.dreamstime.com/b/panadería-italiana-28379528.jpg?w=768",
            contentDescription = "Banner principal del Home",
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
            )
        }
        item {
            Spacer(Modifier.height(20.dp))
            Text(text = "Bienvenido!",
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
            Spacer(Modifier.height(30.dp))
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text("Trozos")
                VerticalDivider(color = MaterialTheme.colorScheme.secondary)
                Text("Fondant")
                VerticalDivider(color = MaterialTheme.colorScheme.secondary)
                Text("Bizcocho")
            }
            Spacer(Modifier.height(30.dp))
        }
        items(products) { product ->
            ProductCard(product = product)
        }
    }
}
