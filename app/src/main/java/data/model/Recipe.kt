package com.example.utilityapp.data.model

data class RecipeResponse(
    val meals: List<Recipe>?
)

data class Recipe(
    val strMeal: String,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?
) {
    fun getIngredients(): List<String> {
        return listOfNotNull(
            strIngredient1, strIngredient2, strIngredient3
        ).filter { !it.isNullOrBlank() }
    }

    fun getMeasures(): List<String> {
        return listOfNotNull(
            strMeasure1, strMeasure2, strMeasure3
        ).filter { !it.isNullOrBlank() }
    }
}