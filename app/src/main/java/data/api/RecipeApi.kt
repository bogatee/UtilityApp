package com.example.utilityapp.data.api

import com.example.utilityapp.data.model.RecipeResponse
import retrofit2.http.GET

interface RecipeApi {
    @GET("random.php")
    suspend fun getRandomRecipe(): RecipeResponse
}