package com.example.utilityapp.data.model

data class CurrencyResponse(
    val rates: Map<String, Double>,
    val base: String,
    val date: String
)

data class ConversionResult(
    val fromCurrency: String,
    val toCurrency: String,
    val amount: Double,
    val convertedAmount: Double,
    val rate: Double
)