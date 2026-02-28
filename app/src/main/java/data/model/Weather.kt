package com.example.utilityapp.data.model

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val name: String
) {
    data class Main(
        val temp: Double,
        val feels_like: Double,
        val humidity: Int
    )

    data class Weather(
        val description: String,
        val icon: String
    )
}

data class WeatherDisplay(
    val city: String,
    val temperature: String,
    val feelsLike: String,
    val humidity: String,
    val description: String
)