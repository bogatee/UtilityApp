package com.example.utilityapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.utilityapp.presentation.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel,
    onNavigateBack: () -> Unit
) {
    val weather by viewModel.weather.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    var cityInput by remember { mutableStateOf("") }
    var searchedCity by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Weather Forecast",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = cityInput,
            onValueChange = { cityInput = it },
            label = { Text("Enter city name") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (cityInput.isNotBlank()) {
                    viewModel.getWeather(cityInput)
                    searchedCity = true
                }
            },
            enabled = !isLoading && cityInput.isNotBlank()
        ) {
            Text(if (isLoading) "Loading..." else "Get Weather")
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else if (error != null) {
            Text(
                text = error ?: "Error",
                color = MaterialTheme.colorScheme.error
            )
        } else if (weather != null && searchedCity) {
            WeatherDisplay(weather = weather!!)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateBack) {
            Text("Back to Home")
        }
    }
}

@Composable
fun WeatherDisplay(weather: com.example.utilityapp.data.model.WeatherDisplay) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = weather.city,
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = weather.temperature,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Feels like ${weather.feelsLike}",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Humidity: ${weather.humidity}",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = weather.description,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}