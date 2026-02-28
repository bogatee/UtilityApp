package com.example.utilityapp.data.repository

import com.example.utilityapp.data.api.WeatherApi
import com.example.utilityapp.data.model.WeatherDisplay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WeatherRepository(private val api: WeatherApi) {
    private val _weather = MutableStateFlow<WeatherDisplay?>(null)
    val weather: StateFlow<WeatherDisplay?> = _weather.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // Your actual API key
    private val apiKey = "b91bac445738d2f8c1a3a805eae96b1f"

    suspend fun getWeather(city: String) {
        _isLoading.value = true
        _error.value = null

        try {
            val response = api.getWeather(city, apiKey, "metric")
            _weather.value = WeatherDisplay(
                city = response.name,
                temperature = "${response.main.temp}°C",
                feelsLike = "${response.main.feels_like}°C",
                humidity = "${response.main.humidity}%",
                description = response.weather.firstOrNull()?.description ?: "Unknown"
            )
        } catch (e: Exception) {
            _error.value = "Error: ${e.message}"
            _weather.value = null
        } finally {
            _isLoading.value = false
        }
    }
}