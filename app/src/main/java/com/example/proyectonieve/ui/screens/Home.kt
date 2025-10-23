package com.example.proyectonieve.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview



@Composable
fun Home(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.White, Color.LightGray)
                )
            )
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://thumbs.dreamstime.com/b/panadería-italiana-28379528.jpg?w=768",
            contentDescription = "Banner principal del Home",
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )

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




        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 0.dp),
            horizontalAlignment = Alignment.Start
        ) {
            AsyncImage(
                model = "https://th.bing.com/th/id/R.634fa577892c3ff6717ddc8c97a396d9?rik=6pyiG1yVAsgFcA&riu=http%3a%2f%2fwww.taste.com.au%2fimages%2frecipes%2fagt%2f2008%2f05%2f19621.jpg&ehk=5yT6egtaJbt%2bAOKsKjm5tJmh3i%2b0OcKZcPvEIHuQy%2fQ%3d&risl=&pid=ImgRaw&r=0",
                contentDescription = "Imagen 1",
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 15.dp)
            )
            AsyncImage(
                model = "https://www.chocolatto.co/wp-content/uploads/2020/07/IMG_0170-scaled.jpg",
                contentDescription = "Imagen 2",
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 15.dp)
            )
            AsyncImage(
                model = "https://www.rama.com.co/-/media/Project/Upfield/Brands/Rama/Rama-CO/Assets/Recipes/sync-img/5c3923a9-ab27-4d26-aaa1-9500924c666f.png?rev=96b46b00ed0a4007ace051f2b1161f54&w=900",
                contentDescription = "Imagen 3",
                modifier = Modifier.size(150.dp)
            )
        }
    }
}
