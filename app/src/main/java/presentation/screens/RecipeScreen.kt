package com.example.utilityapp.presentation.screens

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
import com.example.utilityapp.presentation.viewmodel.RecipeViewModel

@Composable
fun RecipeScreen(
    viewModel: RecipeViewModel,
    onNavigateBack: () -> Unit
) {
    val recipe by viewModel.recipe.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Random Recipe",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.getRandomRecipe() },
            enabled = !isLoading
        ) {
            Text(if (isLoading) "Loading..." else "Get New Recipe")
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else if (error != null) {
            Text(
                text = error ?: "Error",
                color = MaterialTheme.colorScheme.error
            )
        } else if (recipe != null) {
            RecipeDisplay(recipe = recipe!!)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateBack) {
            Text("Back to Home")
        }
    }
}

@Composable
fun RecipeDisplay(recipe: com.example.utilityapp.data.model.Recipe) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = recipe.strMeal,
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "${recipe.strCategory} • ${recipe.strArea}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ingredients:",
            style = MaterialTheme.typography.titleMedium
        )

        val ingredients = recipe.getIngredients()
        val measures = recipe.getMeasures()

        for (i in ingredients.indices) {
            Text(
                text = "• ${ingredients[i]}: ${if (i < measures.size) measures[i] else ""}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Instructions:",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = recipe.strInstructions.take(200) + "...",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}