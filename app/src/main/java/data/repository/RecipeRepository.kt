package com.example.utilityapp.data.repository

import com.example.utilityapp.data.api.RecipeApi
import com.example.utilityapp.data.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecipeRepository(private val api: RecipeApi) {
    private val _recipe = MutableStateFlow<Recipe?>(null)
    val recipe: StateFlow<Recipe?> = _recipe.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    suspend fun getRandomRecipe() {
        _isLoading.value = true
        _error.value = null
        try {
            val response = api.getRandomRecipe()
            _recipe.value = response.meals?.firstOrNull()
        } catch (e: Exception) {
            _error.value = "Error: ${e.message}"
        } finally {
            _isLoading.value = false
        }
    }
}