package com.example.utilityapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utilityapp.data.repository.CurrencyRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CurrencyViewModel(private val repository: CurrencyRepository) : ViewModel() {
    val rates: StateFlow<Map<String, Double>?> = repository.rates
    val isLoading: StateFlow<Boolean> = repository.isLoading
    val error: StateFlow<String?> = repository.error
    val conversionResult = repository.conversionResult
    val currencies: List<String> = repository.currencies

    init {
        loadRates()
    }

    fun loadRates() {
        viewModelScope.launch {
            repository.loadRates()
        }
    }

    fun convert(amount: Double, from: String, to: String) {
        repository.convert(amount, from, to)
    }
}