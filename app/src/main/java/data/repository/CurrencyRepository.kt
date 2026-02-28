package com.example.utilityapp.data.repository

import com.example.utilityapp.data.api.CurrencyApi
import com.example.utilityapp.data.model.ConversionResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CurrencyRepository(private val api: CurrencyApi) {
    private val _rates = MutableStateFlow<Map<String, Double>?>(null)
    val rates: StateFlow<Map<String, Double>?> = _rates.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _conversionResult = MutableStateFlow<ConversionResult?>(null)
    val conversionResult: StateFlow<ConversionResult?> = _conversionResult.asStateFlow()

    val currencies = listOf(
        "USD", "EUR", "GBP", "JPY", "CAD", "AUD", "CHF", "CNY", "INR",  // Major currencies
        "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AWG", "AZN",  // More currencies
        "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB",
        "BRL", "BSD", "BTN", "BWP", "BYN", "BZD", "CDF", "CLP", "COP",
        "CRC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP",
        "ERN", "ETB", "FJD", "FKP", "FOK", "GEL", "GGP", "GHS", "GIP",
        "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF",
        "IDR", "ILS", "IMP", "IQD", "IRR", "ISK", "JEP", "JMD", "JOD",
        "KES", "KGS", "KHR", "KID", "KMF", "KRW", "KWD", "KYD", "KZT",
        "LAK", "LBP", "LKR", "LRD", "LSL", "LYD", "MAD", "MDL", "MGA",
        "MKD", "MMK", "MNT", "MOP", "MRU", "MUR", "MVR", "MWK", "MXN",
        "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR",
        "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON",
        "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD",
        "SHP", "SLE", "SLL", "SOS", "SRD", "SSP", "STN", "SYP", "SZL",
        "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TVD", "TWD",
        "TZS", "UAH", "UGX", "UYU", "UZS", "VES", "VND", "VUV", "WST",
        "XAF", "XCD", "XDR", "XOF", "XPF", "YER", "ZAR", "ZMW", "ZWL"
    )

    suspend fun loadRates(base: String = "USD") {
        _isLoading.value = true
        _error.value = null
        try {
            val response = api.getLatestRates(base)
            _rates.value = response.rates
        } catch (e: Exception) {
            _error.value = "Error: ${e.message}"
        } finally {
            _isLoading.value = false
        }
    }

    fun convert(amount: Double, from: String, to: String) {
        val ratesMap = _rates.value ?: return
        val fromRate = ratesMap[from] ?: 1.0
        val toRate = ratesMap[to] ?: 1.0

        val converted = amount * (toRate / fromRate)

        _conversionResult.value = ConversionResult(
            fromCurrency = from,
            toCurrency = to,
            amount = amount,
            convertedAmount = converted,
            rate = toRate / fromRate
        )
    }
}