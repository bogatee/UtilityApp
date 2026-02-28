package com.example.utilityapp.utils

import android.content.Context
import android.content.SharedPreferences

object PreferencesHelper {
    private const val PREFS_NAME = "app_prefs"
    private const val KEY_LANGUAGE = "selected_language"
    private const val KEY_BACKGROUND_COLOR = "background_color"
    private const val KEY_MUSIC_ENABLED = "music_enabled"

    // Save language
    fun saveLanguage(context: Context, language: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_LANGUAGE, language).apply()
    }

    // Get language
    fun getLanguage(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_LANGUAGE, "English") ?: "English"
    }

    // Save background color (optional)
    fun saveBackgroundColor(context: Context, color: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_BACKGROUND_COLOR, color).apply()
    }

    // Get background color (optional)
    fun getBackgroundColor(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_BACKGROUND_COLOR, "Light") ?: "Light"
    }

    // Save music setting (optional)
    fun saveMusicEnabled(context: Context, enabled: Boolean) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_MUSIC_ENABLED, enabled).apply()
    }

    // Get music setting (optional)
    fun getMusicEnabled(context: Context): Boolean {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_MUSIC_ENABLED, false)
    }

    // Clear all settings (if needed)
    fun clearAll(context: Context) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }
}