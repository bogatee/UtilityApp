package com.example.utilityapp.utils

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LanguageManager {

    fun applyLanguage(context: Context, languageName: String): Context {
        val locale = when (languageName) {
            "Spanish" -> Locale("es")
            "French" -> Locale("fr")
            "German" -> Locale("de")
            "Japanese" -> Locale("ja")
            else -> Locale.ENGLISH // Default to English
        }

        Locale.setDefault(locale)

        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)

        return context.createConfigurationContext(configuration)
    }

    fun getLanguageCode(languageName: String): String {
        return when (languageName) {
            "Spanish" -> "es"
            "French" -> "fr"
            "German" -> "de"
            "Japanese" -> "ja"
            else -> "en"
        }
    }
}