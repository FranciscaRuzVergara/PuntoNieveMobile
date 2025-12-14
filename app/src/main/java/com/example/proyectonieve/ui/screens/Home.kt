package com.example.proyectonieve.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.*
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
import com.example.proyectonieve.sesion.SessionManager
import com.example.proyectonieve.ui.components.ClimaCard
import com.example.proyectonieve.ui.network.RetrofitInstance
import kotlinx.coroutines.launch

@Composable
fun Home(navController: NavController) {

    var temperature by remember { mutableStateOf<Double?>(null) }
    var isLoadingWeather by remember { mutableStateOf(true) }
    var weatherError by remember { mutableStateOf<String?>(null) }

    var products by remember { mutableStateOf<List<Producto>>(emptyList()) }
    var error by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()



    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val API_KEY = "ezOHlkSoXN86sZm3"
                val LAT = "-33.4569"
                val LON = "-70.6483"
                val ASL = 556

                isLoadingWeather = true

                val response = RetrofitInstance.climaApi.getWeather(
                    apiKey = API_KEY,
                    lat = LAT,
                    lon = LON,
                    asl = ASL
                )
                val temp = response.data_1h?.temperature?.firstOrNull()
                if (temp != null) {
                    temperature = temp
                } else {
                    weatherError = "Estructura de clima inválida"
                }
            } catch (e: Exception) {
                println("Error al obtener clima: ${e.message}")
                weatherError = "No se pudo cargar el clima."
            } finally {
                isLoadingWeather = false
            }
        }

        scope.launch {
            try {
                val response = RetrofitInstance.productoApi.listarProductos()
                println("PRODUCTOS RECIBIDOS DESDE BACKEND: $response")
                products = response
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

        /***Tarjeta clima**/
        item {
            Spacer(Modifier.height(20.dp))
            ClimaCard(
                temperature = temperature,
                isLoading = isLoadingWeather,
                error = weatherError
            )
            Spacer(Modifier.height(20.dp))
        }

        item {
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Bienvenido!",
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

        if (error != null) {
            item {
                Text(
                    text = error!!,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        items(products) { product ->
            ProductCard(product = product)
        }
    }
}
