package com.example.proyectonieve.ui.network

import com.example.proyectonieve.data.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ClimaApi {
    @GET("packages/basic-1h")
    suspend fun getWeather(
        @Query("apikey") apiKey: String,
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("asl") asl: Int,
        @Query("format") format: String = "json"
    ): WeatherResponse
}