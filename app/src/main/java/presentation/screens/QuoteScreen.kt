package com.example.utilityapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.utilityapp.presentation.viewmodel.QuoteViewModel

@Composable
fun QuoteScreen(
    viewModel: QuoteViewModel,
    onNavigateBack: () -> Unit
) {
    val quote by viewModel.quote.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Quote of the Day",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else if (error != null) {
            Text(
                text = error ?: "Error",
                color = MaterialTheme.colorScheme.error
            )
        } else {
            quote?.let {
                Text(
                    text = "\"${it.text}\"",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "- ${it.author}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.getRandomQuote() },
            enabled = !isLoading
        ) {
            Text(if (isLoading) "Loading..." else "New Quote")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onNavigateBack) {
            Text("Back to Home")
        }
    }
}