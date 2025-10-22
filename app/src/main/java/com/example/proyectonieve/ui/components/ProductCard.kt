package com.example.proyectonieve.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectonieve.ui.theme.ProyectoNieveTheme

/***
@Composable
fun ProductCard() {
    Card() {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "product.name", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(4.dp))
            Text(text = "product.price", style = MaterialTheme.typography.titleMedium, color = Color.Green)
            Spacer(Modifier.height(8.dp))
            Text(text = "product.description", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
***/


@Composable
fun ProductCard(product: Product, onProductClick: (Product) -> Unit) {
    Card(onClick = { onProductClick(product) }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.name, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(4.dp))
            Text(text = "$${product.price}", style = MaterialTheme.typography.titleMedium, color = Color.Green)
            Spacer(Modifier.height(8.dp))
            Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}


data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String
)

@Preview(showBackground = true, name = "Product Card Preview")
@Composable
fun ProductCardPreview() {
    val sampleProduct = Product(
        id = 1,
        name = "Selva negra",
        price = 15.990,
        description = "Bizcocho de chocolate"
    )

    ProyectoNieveTheme {
        ProductCard(
            product = sampleProduct,
            onProductClick = {}
        )
    }
}