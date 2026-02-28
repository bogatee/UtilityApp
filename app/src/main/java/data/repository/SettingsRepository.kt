package com.example.utilityapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.utilityapp.utils.PreferencesHelper  // Add this import
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsRepository(private val context: Context) {

    companion object {
        val BACKGROUND_COLOR_KEY = stringPreferencesKey("background_color")
        val MUSIC_ENABLED_KEY = booleanPreferencesKey("music_enabled")
        val LANGUAGE_KEY = stringPreferencesKey("language")
    }

    val backgroundColor: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[BACKGROUND_COLOR_KEY] ?: "Light" }

    val musicEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[MUSIC_ENABLED_KEY] ?: false }

    val language: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[LANGUAGE_KEY] ?: "English" }

    suspend fun setBackgroundColor(color: String) {
        context.dataStore.edit { preferences ->
            preferences[BACKGROUND_COLOR_KEY] = color
        }
    }

    suspend fun setMusicEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[MUSIC_ENABLED_KEY] = enabled
        }
    }

    // Regular language setter (kept for compatibility)
    suspend fun setLanguage(language: String) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE_KEY] = language
        }
    }

    // NEW: Language setter that also saves to SharedPreferences for restart
    suspend fun setLanguageWithRestart(language: String) {
        // Save to DataStore
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE_KEY] = language
        }
        // Also save to SharedPreferences for BaseActivity to read during restart
        PreferencesHelper.saveLanguage(context, language)
    }
}