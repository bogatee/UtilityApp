package com.example.utilityapp

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.utilityapp.presentation.screens.CurrencyScreen
import com.example.utilityapp.presentation.screens.HomeScreen
import com.example.utilityapp.presentation.screens.QuoteScreen
import com.example.utilityapp.presentation.screens.RecipeScreen
import com.example.utilityapp.presentation.screens.SettingsScreen
import com.example.utilityapp.presentation.screens.WeatherScreen
import com.example.utilityapp.presentation.viewmodel.CurrencyViewModel
import com.example.utilityapp.presentation.viewmodel.QuoteViewModel
import com.example.utilityapp.presentation.viewmodel.RecipeViewModel
import com.example.utilityapp.presentation.viewmodel.SettingsViewModel
import com.example.utilityapp.presentation.viewmodel.WeatherViewModel
import com.example.utilityapp.ui.theme.UtilityAppTheme
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

class MainActivity : BaseActivity() {

    // Fixed: This should not return anything (Unit)
    override fun attachBaseContext(newBase: Context) {
        // Just pass through - language is handled in BaseActivity
        super.attachBaseContext(newBase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            UtilityAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UtilityApp()
                }
            }
        }
    }
}

@Composable
fun UtilityApp() {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf(0) }
    val context = LocalContext.current

    // Track if we've already triggered a restart
    var restartTriggered by remember { mutableStateOf(false) }

    // Get Settings ViewModel
    val settingsViewModel: SettingsViewModel = koinViewModel()
    val backgroundColor by settingsViewModel.backgroundColor.collectAsState(initial = "Light")
    val language by settingsViewModel.language.collectAsState(initial = "English")

    // Watch for language changes - but only restart once
    LaunchedEffect(language) {
        if (!restartTriggered && context is MainActivity) {
            // Get the saved language from preferences to compare
            val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            val savedLanguage = prefs.getString("selected_language", "English") ?: "English"

            // Only restart if language actually changed and we haven't restarted yet
            if (language != savedLanguage && !restartTriggered) {
                restartTriggered = true
                delay(500) // Small delay to ensure save completes
                context.restartApp()
            }
        }
    }

    val items = listOf(
        "Home" to Icons.Default.Home,
        "Quote" to Icons.Default.ThumbUp,
        "Currency" to Icons.Outlined.CurrencyExchange,
        "Weather" to Icons.Default.Cloud,
        "Recipe" to Icons.Default.Restaurant,
        "Settings" to Icons.Default.Settings
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, pair ->
                    NavigationBarItem(
                        icon = { Icon(pair.second, contentDescription = pair.first) },
                        label = { Text(pair.first) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            when (index) {
                                0 -> navController.navigate("home") {
                                    popUpTo("home") { inclusive = true }
                                }
                                1 -> navController.navigate("quote")
                                2 -> navController.navigate("currency")
                                3 -> navController.navigate("weather")
                                4 -> navController.navigate("recipe")
                                5 -> navController.navigate("settings")
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(
                    backgroundColor = backgroundColor,
                    onNavigateToQuote = {
                        selectedItem = 1
                        navController.navigate("quote")
                    },
                    onNavigateToCurrency = {
                        selectedItem = 2
                        navController.navigate("currency")
                    },
                    onNavigateToWeather = {
                        selectedItem = 3
                        navController.navigate("weather")
                    },
                    onNavigateToRecipe = {
                        selectedItem = 4
                        navController.navigate("recipe")
                    },
                    onNavigateToSettings = {
                        selectedItem = 5
                        navController.navigate("settings")
                    }
                )
            }

            composable("quote") {
                val viewModel: QuoteViewModel = koinViewModel()
                QuoteScreen(
                    viewModel = viewModel,
                    onNavigateBack = {
                        selectedItem = 0
                        navController.popBackStack()
                    }
                )
            }

            composable("currency") {
                val viewModel: CurrencyViewModel = koinViewModel()
                CurrencyScreen(
                    viewModel = viewModel,
                    onNavigateBack = {
                        selectedItem = 0
                        navController.popBackStack()
                    }
                )
            }

            composable("weather") {
                val viewModel: WeatherViewModel = koinViewModel()
                WeatherScreen(
                    viewModel = viewModel,
                    onNavigateBack = {
                        selectedItem = 0
                        navController.popBackStack()
                    }
                )
            }

            composable("recipe") {
                val viewModel: RecipeViewModel = koinViewModel()
                RecipeScreen(
                    viewModel = viewModel,
                    onNavigateBack = {
                        selectedItem = 0
                        navController.popBackStack()
                    }
                )
            }

            composable("settings") {
                val viewModel: SettingsViewModel = koinViewModel()
                SettingsScreen(
                    viewModel = viewModel,
                    onNavigateBack = {
                        selectedItem = 0
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}