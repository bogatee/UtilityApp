package com.example.utilityapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utilityapp.data.repository.WeatherRepository  // ADD THIS IMPORT
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    val weather = repository.weather
    val isLoading: StateFlow<Boolean> = repository.isLoading
    val error: StateFlow<String?> = repository.error

    fun getWeather(city: String) {
        viewModelScope.launch {
            repository.getWeather(city)
        }
    }
}