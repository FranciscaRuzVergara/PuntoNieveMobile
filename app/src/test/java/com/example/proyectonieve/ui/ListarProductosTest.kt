package com.example.proyectonieve.ui

import com.example.proyectonieve.data.Producto
import com.example.proyectonieve.ui.network.ProductoApi
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

class ListarProductosTest : StringSpec({

    "listarProductos devuelve lista mockeada" {
        val productoApi = mockk<ProductoApi>()

        val productosMock = listOf(
            Producto(1L, 10000, "Torta Chocolate", "desc", null, "Torta"),
            Producto(2L, 5000, "Galleta", "desc", null, "Galleta")
        )

        coEvery { productoApi.listarProductos() } returns productosMock

        val resultado = productoApi.listarProductos()

        resultado.size shouldBe 2
        resultado[0].nombreProducto shouldBe "Torta Chocolate"
    }
})
