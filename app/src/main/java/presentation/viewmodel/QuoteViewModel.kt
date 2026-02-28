package com.example.utilityapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utilityapp.data.repository.QuoteRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {
    val quote: StateFlow<com.example.utilityapp.data.model.Quote?> = repository.quote
    val isLoading: StateFlow<Boolean> = repository.isLoading
    val error: StateFlow<String?> = repository.error

    fun getRandomQuote() {
        viewModelScope.launch {
            repository.getRandomQuote()
        }
    }
}