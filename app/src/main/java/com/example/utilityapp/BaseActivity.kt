package com.example.utilityapp

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import com.example.utilityapp.utils.LanguageManager
import com.example.utilityapp.utils.PreferencesHelper

abstract class BaseActivity : ComponentActivity() {

    // This should return Unit (nothing)
    override fun attachBaseContext(newBase: Context) {
        // Get saved language from SharedPreferences using PreferencesHelper
        val language = PreferencesHelper.getLanguage(newBase)
        val context = LanguageManager.applyLanguage(newBase, language)
        super.attachBaseContext(context)
    }

    fun restartApp() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }
}