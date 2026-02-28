package com.example.utilityapp.data.api

import com.example.utilityapp.data.model.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    @GET("latest")
    suspend fun getLatestRates(
        @Query("base") base: String = "USD"
    ): CurrencyResponse
}