package com.example.utilityapp.data.api

import retrofit2.http.GET

interface QuoteApi {
    @GET("random")
    suspend fun getRandomQuote(): QuoteResponse

    data class QuoteResponse(
        val content: String,
        val author: String
    )
}