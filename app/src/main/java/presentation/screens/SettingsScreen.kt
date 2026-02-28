package com.example.utilityapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.utilityapp.presentation.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onNavigateBack: () -> Unit
) {
    val backgroundColor by viewModel.backgroundColor.collectAsState(initial = "Light")
    val musicEnabled by viewModel.musicEnabled.collectAsState(initial = false)
    val language by viewModel.language.collectAsState(initial = "English")  // NEW

    // Available languages
    val languages = listOf("English", "Spanish", "French", "German", "Japanese")

    // Screen background based on current setting
    val screenBgColor = if (backgroundColor == "Dark") Color(0xFF121212) else Color.White
    val textColor = if (backgroundColor == "Dark") Color.White else Color.Black
    val cardBgColor = if (backgroundColor == "Dark") Color(0xFF1E1E1E) else Color(0xFFF5F5F5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(screenBgColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title with emoji
        Text(
            text = "⚙️ Settings",
            style = MaterialTheme.typography.headlineMedium,
            color = textColor
        )

        Spacer(modifier = Modifier.height(32.dp))

        // ===== BACKGROUND COLOR SECTION =====
        Text(
            text = "🎨 Background Color",
            style = MaterialTheme.typography.titleLarge,
            color = textColor,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Light Mode Button
            Button(
                onClick = { viewModel.setBackgroundColor("Light") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (backgroundColor == "Light") Color(0xFF2196F3) else Color.Gray
                )
            ) {
                Text("☀️ Light")
            }

            // Dark Mode Button
            Button(
                onClick = { viewModel.setBackgroundColor("Dark") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (backgroundColor == "Dark") Color(0xFF2196F3) else Color.Gray
                )
            ) {
                Text("🌙 Dark")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))

        // ===== LANGUAGE SECTION ===== (NEW)
        Text(
            text = "🌐 Language",
            style = MaterialTheme.typography.titleLarge,
            color = textColor,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Language selection buttons in a grid (2 per row)
        languages.chunked(2).forEach { rowLanguages ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowLanguages.forEach { lang ->
                    Button(
                        onClick = { viewModel.setLanguage(lang) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (language == lang) Color(0xFF4CAF50) else Color.Gray
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp)
                    ) {
                        Text(lang)
                    }
                }
                // If odd number, add empty spacer
                if (rowLanguages.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))

        // ===== MUSIC SECTION =====
        Text(
            text = "🎵 Background Music",
            style = MaterialTheme.typography.titleLarge,
            color = textColor,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (musicEnabled) "Music: ON 🔊" else "Music: OFF 🔇",
                style = MaterialTheme.typography.bodyLarge,
                color = textColor
            )

            Switch(
                checked = musicEnabled,
                onCheckedChange = { viewModel.setMusicEnabled(it) }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))

        // ===== CURRENT SETTINGS SUMMARY =====
        Text(
            text = "📋 Current Settings:",
            style = MaterialTheme.typography.titleMedium,
            color = textColor
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Settings summary card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(cardBgColor, shape = MaterialTheme.shapes.medium)
                .padding(16.dp)
        ) {
            SettingsRow("Background:", backgroundColor, if (backgroundColor == "Dark") "🌙" else "☀️")
            SettingsRow("Language:", language, "🌐")  // NEW
            SettingsRow("Music:", if (musicEnabled) "ON" else "OFF", if (musicEnabled) "🔊" else "🔇")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Back button
        Button(
            onClick = onNavigateBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("⬅️ Back to Home")
        }
    }
}

// Helper composable for settings rows
@Composable
fun SettingsRow(label: String, value: String, emoji: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$emoji $label",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF2196F3)
        )
    }
}