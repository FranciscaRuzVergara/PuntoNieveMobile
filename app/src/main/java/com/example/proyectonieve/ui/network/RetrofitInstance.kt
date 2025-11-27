package com.example.proyectonieve.ui.network


import com.example.proyectonieve.ui.network.UserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    private const val URL_BACKEND = "http://10.0.2.2:9090/"
    private const val URL_CLIMA = "https://my.meteoblue.com/"
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL_BACKEND)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitClima: Retrofit = Retrofit.Builder()
        .baseUrl(URL_CLIMA)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userApi: UserApi = retrofit.create(UserApi::class.java)

    val productoApi: ProductoApi = retrofit.create(ProductoApi::class.java)

    val pedidoApi: PedidoApi = retrofit.create(PedidoApi::class.java)

    val carritoApi: CarritoApi = retrofit.create(CarritoApi::class.java)

    val climaApi: ClimaApi = retrofitClima.create(ClimaApi::class.java)

}
