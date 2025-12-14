package com.example.proyectonieve.ui

import com.example.proyectonieve.data.Producto
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class NombreProductoTest {
    @Test
    fun nombreNoVacio() {
        val p = Producto(
            1L,
            12000,
            "Torta Tres Leches",
            "Clásica tres leches",
            null,
            "Torta"
        )

        Assertions.assertNotNull(p.nombreProducto, "El nombre no debe ser null")
        Assertions.assertFalse(
            p.nombreProducto.trim { it <= ' ' }.isEmpty(),
            "El nombre no debe estar vacío"
        )
    }

    @Test
    fun nombreVacio() {
        val p = Producto(
            2L,
            12000,
            "   ",
            "Descripción",
            null,
            "Torta"
        )

        Assertions.assertTrue(
            p.nombreProducto.trim { it <= ' ' }.isEmpty(),
            "El nombre es inválido si está vacío"
        )
    }
}
