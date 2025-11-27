package com.example.proyectonieve.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ClimaCard(
    temperature: Double?,
    isLoading: Boolean,
    error: String?
){
    val cardModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(Color(0xFF283593))
        .padding(12.dp)
    Row(
        modifier = cardModifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        when {
            isLoading -> {
                Text(text = "Cargando clima...", color = Color.White)
                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
            }
            error != null -> {
                Text(text = error, color = Color(0xFFFFCC80), fontWeight = FontWeight.Medium)
            }
            temperature != null -> {
                Column {
                    Text(text = "Clima en Santiago", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Temperatura actual", color = Color.LightGray, fontSize = 12.sp)
                }
                Text(text = "${temperature}°C", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.ExtraBold)
            }
            else -> {
                Text(text = "Clima no disponible.", color = Color.LightGray)
            }
        }
    }
}