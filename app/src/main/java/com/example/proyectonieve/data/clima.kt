package com.example.proyectonieve.data

import android.health.connect.datatypes.units.Temperature

data class WeatherResponse(
    val data_1h: Data1h?
)

data class Data1h(
    val temperature: List<Double>?
)