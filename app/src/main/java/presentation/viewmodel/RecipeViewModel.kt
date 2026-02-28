package com.example.utilityapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utilityapp.data.repository.RecipeRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {
    val recipe = repository.recipe
    val isLoading: StateFlow<Boolean> = repository.isLoading
    val error: StateFlow<String?> = repository.error

    fun getRandomRecipe() {
        viewModelScope.launch {
            repository.getRandomRecipe()
        }
    }
}