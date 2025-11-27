package com.example.proyectonieve.ui

import com.example.proyectonieve.data.Producto
import org.junit.Assert.assertTrue
import org.junit.Test

class PrecioProductoTest {

    @Test
    fun precioPositivo(){
        val productoValido = Producto(
            1,
            10000,
            "Torta de Piña",
            "Torta de bizcocho de vainilla con trozos de piña",
            null,
            "Torta"
        )
        val precio = productoValido.precio
        assertTrue("El precio del producto debe ser positivo",precio>0)
    }

    @Test
    fun precioNegativo(){
        val productoNoValido = Producto(
            1,
            -15000,
            "Torta de chocolate",
            "Torta de bizcocho de chocolate",
            null,
            "Torta"
        )
        val precioNegativo = productoNoValido.precio
        assertTrue("El precio es negativo o cero", precioNegativo<=0)
    }
}