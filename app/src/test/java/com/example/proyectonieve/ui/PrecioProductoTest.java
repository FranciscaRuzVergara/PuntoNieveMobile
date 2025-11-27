package com.example.proyectonieve.ui;

import com.example.proyectonieve.data.Producto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrecioProductoTest {

    @Test
    void precioPositivo() {
        Producto productoValido = new Producto(
                1L,
                10000,
                "Torta de Piña",
                "Torta de bizcocho de vainilla con trozos de piña",
                null,
                "Torta"
        );

        assertTrue(productoValido.getPrecio() > 0, "El precio del producto debe ser positivo");
    }

    @Test
    void precioNegativo() {
        Producto productoNoValido = new Producto(
                2L,
                -15000,
                "Torta de chocolate",
                "Torta de bizcocho de chocolate",
                null,
                "Torta"
        );

        assertTrue(productoNoValido.getPrecio() <= 0, "El precio es negativo o cero");
    }
}
