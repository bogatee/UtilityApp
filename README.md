UtilityApp
A utility application for Android that provides quick access to useful daily information.

Features:
Quote of the Day:
Displays random inspirational quotes. If the internet connection is unavailable, the app shows saved quotes as a fallback option.

Currency Converter:
Converts between different currencies using real-time exchange rates. Users can select from multiple currency options.

Weather Forecast:
Shows current weather conditions for any city using live data from OpenWeatherMap. Displays temperature, feels like temperature, humidity, and weather description.

Random Recipes:
Presents random recipes from around the world including ingredients and cooking instructions.

Settings:
Allows users to customize their experience with the following options:

Light or Dark theme

Language selection (English, Spanish, French, German, Japanese)

Background music on/off toggle

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

Screenshots:

UtilityApp Home:
<img width="470" height="1012" alt="image" src="https://github.com/user-attachments/assets/18071e62-482e-46ac-9568-0a0dc5173d24" />

Quote of the day:
<img width="463" height="1003" alt="image" src="https://github.com/user-attachments/assets/514a7c63-c88d-4823-89c1-b7ad7ed814f8" />

Currency Converter
<img width="466" height="1010" alt="image" src="https://github.com/user-attachments/assets/31a769a0-7bd7-4315-9e02-78d62f4f803a" />

Weather Forecast:
<img width="468" height="1006" alt="image" src="https://github.com/user-attachments/assets/0d01d341-4310-41d4-b0ec-ef44b0ff4430" />

Food Recipe:
<img width="468" height="1007" alt="image" src="https://github.com/user-attachments/assets/df73cd4d-2c68-48de-8e5e-f0769783b4a1" />

Settings:
<img width="462" height="1013" alt="image" src="https://github.com/user-attachments/assets/0448d94f-56f6-4b9d-bb49-7cfc18eb7588" />

-------------------------------------------------------------------------------------------------

Technologies Used:
Kotlin programming language
Jetpack Compose for UI development
Material Design 3 for consistent styling
ViewModel and StateFlow for managing app data
Koin for dependency injection
Retrofit for API communication
DataStore and SharedPreferences for saving user settings

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

APIs Used:
Quotable API - Provides random quotes (https://github.com/lukePeavey/quotable)
Frankfurter API - Supplies currency exchange rates (https://www.frankfurter.app/)
OpenWeatherMap API - Delivers weather data (https://openweathermap.org/)
TheMealDB API - Offers random recipe information (https://www.themealdb.com/)

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

How to Run the Project:
Download or clone the repository from GitHub
Open the project in Android Studio
Add your personal OpenWeatherMap API key in the WeatherRepository.kt file
Build and run the app on an emulator or physical device (minimum Android SDK version 26 required)

----------------------------------------------------------------------------------------------------------------------------------------------------------------------

About the Project:
This application was developed as part of an advanced mobile technology assignment. 
It demonstrates the use of modern Android development practices including clean architecture, dependency injection, and working with external web APIs.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------
