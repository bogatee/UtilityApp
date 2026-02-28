package com.example.utilityapp.data.repository

import com.example.utilityapp.data.api.QuoteApi
import com.example.utilityapp.data.model.Quote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuoteRepository(private val api: QuoteApi) {
    private val _quote = MutableStateFlow<Quote?>(null)
    val quote: StateFlow<Quote?> = _quote.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // Mock quotes for when API fails
    private val mockQuotes = listOf(
        Quote("The only way to do great work is to love what you do.", "Steve Jobs"),
        Quote("Life is what happens when you're busy making other plans.", "John Lennon"),
        Quote("The future belongs to those who believe in the beauty of their dreams.", "Eleanor Roosevelt"),
        Quote("Success is not final, failure is not fatal: it is the courage to continue that counts.", "Winston Churchill"),
        Quote("Believe you can and you're halfway there.", "Theodore Roosevelt"),
        Quote("Don't watch the clock; do what it does. Keep going.", "Sam Levenson"),
        Quote("The only limit to our realization of tomorrow will be our doubts of today.", "Franklin D. Roosevelt"),
        Quote("Everything you've ever wanted is on the other side of fear.", "George Addair"),
        Quote("The future depends on what you do today.", "Mahatma Gandhi"),
        Quote("The harder you work for something, the greater you'll feel when you achieve it.", "Anonymous"),
        Quote("It does not matter how slowly you go as long as you do not stop.", "Confucius"),
        Quote("Your time is limited, don't waste it living someone else's life.", "Steve Jobs"),
        Quote("The best way to predict the future is to create it.", "Peter Drucker"),
        Quote("You miss 100% of the shots you don't take.", "Wayne Gretzky"),
        Quote("Whether you think you can or you think you can't, you're right.", "Henry Ford")
    )

    suspend fun getRandomQuote() {
        _isLoading.value = true
        _error.value = null
        try {
            // Try to get quote from API
            val response = api.getRandomQuote()
            _quote.value = Quote(response.content, response.author)
        } catch (e: Exception) {
            // If API fails, use mock quotes - no error shown to user
            _quote.value = mockQuotes.random()
            _error.value = null  // Clear any error
        } finally {
            _isLoading.value = false
        }
    }
}