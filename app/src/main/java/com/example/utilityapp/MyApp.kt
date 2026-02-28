package com.example.utilityapp

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.utilityapp.di.appModule
import com.example.utilityapp.utils.LanguageManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

val Context.dataStore by preferencesDataStore(name = "settings")

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApp)
            modules(appModule)
        }
    }

    override fun attachBaseContext(base: Context) {
        // Get saved language and apply it before attaching
        val language = getSavedLanguage(base)
        val context = LanguageManager.applyLanguage(base, language)
        super.attachBaseContext(context)
    }

    private fun getSavedLanguage(context: Context): String {
        return try {
            // Run blocking to get language from DataStore synchronously
            runBlocking {
                context.dataStore.data.first()[com.example.utilityapp.data.repository.SettingsRepository.Companion.LANGUAGE_KEY] ?: "English"
            }
        } catch (e: Exception) {
            "English" // Default if error
        }
    }
}