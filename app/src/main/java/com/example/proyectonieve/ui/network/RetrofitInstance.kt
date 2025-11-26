package com.example.proyectonieve.ui.network


import com.example.proyectonieve.ui.network.UserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userApi: UserApi = retrofit.create(UserApi::class.java)

    val productoApi: ProductoApi = retrofit.create(ProductoApi::class.java)

    val pedidoApi: PedidoApi = retrofit.create(PedidoApi::class.java)

    val carritoApi: CarritoApi = retrofit.create(CarritoApi::class.java)

}
