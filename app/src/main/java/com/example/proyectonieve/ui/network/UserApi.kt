package com.example.proyectonieve.ui.network

import com.example.proyectonieve.data.User
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("api/usuarios/register")
    suspend fun registerUser(@Body user: User): User
}
