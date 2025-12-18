package com.example.proyectonieve.ui

import com.example.proyectonieve.data.Producto
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.collections.shouldBeOneOf
import io.kotest.matchers.shouldBe

class ProductoTest: WordSpec({

    "Un producto del catálogo" should {

        "pertenecer a una categoría definida" {
            val categoriasPermitidas = listOf("Dulce", "Salado")
            val producto = Producto(null, 1000, "Muffin", null, "", "Dulce")

            producto.categoria shouldBeOneOf categoriasPermitidas
        }

        "tener un ID nulo si es un producto nuevo que aún no se guarda" {
            val nuevoProducto = Producto(null, 500, "Galleta", null, "", "Dulce")

            nuevoProducto.idProducto shouldBe null
        }
    }
})