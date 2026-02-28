package com.example.utilityapp.di

import com.example.utilityapp.data.api.CurrencyApi
import com.example.utilityapp.data.api.QuoteApi
import com.example.utilityapp.data.api.RecipeApi
import com.example.utilityapp.data.api.WeatherApi
import com.example.utilityapp.data.repository.CurrencyRepository
import com.example.utilityapp.data.repository.QuoteRepository
import com.example.utilityapp.data.repository.RecipeRepository
import com.example.utilityapp.data.repository.SettingsRepository
import com.example.utilityapp.data.repository.WeatherRepository
import com.example.utilityapp.presentation.viewmodel.CurrencyViewModel
import com.example.utilityapp.presentation.viewmodel.QuoteViewModel
import com.example.utilityapp.presentation.viewmodel.RecipeViewModel
import com.example.utilityapp.presentation.viewmodel.SettingsViewModel
import com.example.utilityapp.presentation.viewmodel.WeatherViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    // OkHttp
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // APIs
    single<QuoteApi> {
        Retrofit.Builder()
            .baseUrl("https://api.quotable.io/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteApi::class.java)
    }

    single<CurrencyApi> {
        Retrofit.Builder()
            .baseUrl("https://api.frankfurter.app/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyApi::class.java)
    }

    single<WeatherApi> {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    single<RecipeApi> {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApi::class.java)
    }

    // Repositories
    factory<QuoteRepository> { QuoteRepository(get()) }
    factory<CurrencyRepository> { CurrencyRepository(get()) }
    factory<WeatherRepository> { WeatherRepository(get()) }
    factory<RecipeRepository> { RecipeRepository(get()) }
    factory<SettingsRepository> { SettingsRepository(androidContext()) }

    // ViewModels
    viewModelOf(::QuoteViewModel)
    viewModelOf(::CurrencyViewModel)
    viewModelOf(::WeatherViewModel)
    viewModelOf(::RecipeViewModel)
    viewModelOf(::SettingsViewModel)
}