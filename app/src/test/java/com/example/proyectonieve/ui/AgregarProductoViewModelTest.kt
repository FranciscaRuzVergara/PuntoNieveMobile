package com.example.proyectonieve.ui

import com.example.proyectonieve.data.Producto
import com.example.proyectonieve.ui.network.ProductoApi
import com.example.proyectonieve.viewmodel.AgregarProductoViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AgregarProductoViewModelTest {

    @Test
    fun cargarProduct() = runTest {
        val apiFalsa = mockk<ProductoApi>()
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)

        val listaDesdeApi = listOf(
            Producto(1L, 500, "Galleta", null, null, null),
            Producto(2L, 800, "Alfajor", null, null, null)
        )

        coEvery { apiFalsa.listarProductos() } returns listaDesdeApi

        val viewModel = AgregarProductoViewModel(api = apiFalsa, dispatcher = testDispatcher)

        viewModel.cargarProductos()

        assert(viewModel.productos.size == 2)
        assert(viewModel.productos[0].nombreProducto == "Galleta")
    }

    @Test
    fun guardarProduct() = runTest {
        val apiFalsa = mockk<ProductoApi>()

        val testDispatcher = UnconfinedTestDispatcher(testScheduler)

        val productoEjemplo = Producto(
            idProducto = 1L,
            precio = 1000,
            nombreProducto = "Test",
            descripcion = "torta de prueba",
            imagen = "",
            categoria = "Torta"
        )

        val p = Producto(
            idProducto = null,
            precio = 5000,
            nombreProducto = "Nuevo",
            descripcion = "Nuevo producto",
            imagen = "",
            categoria = "Galleta"
        )

        coEvery { apiFalsa.registrarProducto(any()) } returns productoEjemplo
        coEvery { apiFalsa.listarProductos() } returns listOf(productoEjemplo)

        val viewModel = AgregarProductoViewModel(api = apiFalsa, dispatcher = testDispatcher)

        viewModel.guardarProducto(p, esEdicion = false)

        advanceUntilIdle()

        assertFalse(viewModel.isSaving, "El estado isSaving debería ser false al terminar")
        coVerify(exactly = 1) { apiFalsa.registrarProducto(p) }
    }
}