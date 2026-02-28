package com.example.utilityapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utilityapp.data.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SettingsViewModel(private val repository: SettingsRepository) : ViewModel() {
    val backgroundColor: Flow<String> = repository.backgroundColor
    val musicEnabled: Flow<Boolean> = repository.musicEnabled
    val language: Flow<String> = repository.language  // NEW

    fun setBackgroundColor(color: String) {
        viewModelScope.launch {
            repository.setBackgroundColor(color)
        }
    }

    fun setMusicEnabled(enabled: Boolean) {
        viewModelScope.launch {
            repository.setMusicEnabled(enabled)
        }
    }

    // NEW: Add language setter
    fun setLanguage(language: String) {
        viewModelScope.launch {
            repository.setLanguage(language)
        }
    }
}