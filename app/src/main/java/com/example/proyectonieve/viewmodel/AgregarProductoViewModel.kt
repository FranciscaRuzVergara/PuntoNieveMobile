package com.example.proyectonieve.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectonieve.data.Producto
import com.example.proyectonieve.ui.network.ProductoApi
import com.example.proyectonieve.ui.network.RetrofitInstance
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgregarProductoViewModel(private val api: ProductoApi= RetrofitInstance.productoApi,
                               private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    var productos by mutableStateOf<List<Producto>>(emptyList())
    var isSaving by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun cargarProductos() {
        viewModelScope.launch(dispatcher) {
            try {
                productos = api.listarProductos()
            } catch (e: Exception) {
                error = "Error cargando productos: ${e.message}"
            }
        }
    }

    fun guardarProducto(producto: Producto, esEdicion: Boolean) {
        viewModelScope.launch(dispatcher) {
            isSaving = true
            error = null
            try {
                if (esEdicion) {
                    api.actualizarProducto(producto.idProducto!!, producto)
                } else {
                    api.registrarProducto(producto)
                }
                cargarProductos()
            }catch (e: Exception){
                error = "Error al guardar: ${e.message}"
            } finally {
                isSaving = false
            }
        }
    }
}