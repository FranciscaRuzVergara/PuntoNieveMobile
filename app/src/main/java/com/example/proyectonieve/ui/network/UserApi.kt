package com.example.proyectonieve.ui.network

import com.example.proyectonieve.data.User
import retrofit2.http.*


interface UserApi {

        @POST("api/user/register")
        suspend fun registerUser(@Body user: User): User


        @GET("api/user")
        suspend fun listarUsuarios(): List<User>


        @GET("api/user/{id}")
        suspend fun buscarUsuario(@Path("id") id: Long): User


        @PUT("api/user/{id}")
        suspend fun actualizarUsuario(@Path("id") id: Long, @Body user: User): User


        @DELETE("api/user/{id}")
        suspend fun eliminarUsuario(@Path("id") id: Long)

    }