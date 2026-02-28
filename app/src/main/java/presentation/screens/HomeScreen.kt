package com.example.utilityapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    backgroundColor: String,
    onNavigateToQuote: () -> Unit,
    onNavigateToCurrency: () -> Unit,
    onNavigateToWeather: () -> Unit,
    onNavigateToRecipe: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Utility App",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        FeatureCard(
            title = "Quote of the Day",
            description = "Get inspired with random quotes",
            onClick = onNavigateToQuote
        )

        FeatureCard(
            title = "Currency Converter",
            description = "Convert between currencies",
            onClick = onNavigateToCurrency
        )

        FeatureCard(
            title = "Weather Forecast",
            description = "Check current weather",
            onClick = onNavigateToWeather
        )

        FeatureCard(
            title = "Random Recipes",
            description = "Discover new recipes",
            onClick = onNavigateToRecipe
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToSettings,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Settings")
        }
    }
}

@Composable
fun FeatureCard(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}