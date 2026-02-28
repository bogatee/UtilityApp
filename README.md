UtilityApp:

A utility application for Android that provides quick access to useful daily information.

Features:

1. Quote of the Day:
Displays random inspirational quotes. If the internet connection is unavailable, the app shows saved quotes as a fallback option.

2. Currency Converter:
Converts between different currencies using real-time exchange rates. Users can select from multiple currency options.

3. Weather Forecast:
Shows current weather conditions for any city using live data from OpenWeatherMap. Displays temperature, feels like temperature, humidity, and weather description.

4. Random Recipes:
Presents random recipes from around the world including ingredients and cooking instructions.

5. Settings:
Allows users to customize their experience with the following options.

6. Light or Dark theme:
Language selection (English, Spanish, French, German, Japanese).

7. Background music on/off toggle.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

Screenshots:

1. UtilityApp Home:


<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/18071e62-482e-46ac-9568-0a0dc5173d24" />



2. Quote of the day:


<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/514a7c63-c88d-4823-89c1-b7ad7ed814f8" />



3. Currency Converter:


<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/31a769a0-7bd7-4315-9e02-78d62f4f803a" />



4. Weather Forecast:


<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/0d01d341-4310-41d4-b0ec-ef44b0ff4430" />



5. Food Recipe:


<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/df73cd4d-2c68-48de-8e5e-f0769783b4a1" />



6. Settings:


<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/0448d94f-56f6-4b9d-bb49-7cfc18eb7588" />



-------------------------------------------------------------------------------------------------

Technologies Used:
1. Kotlin programming language.
   
2. Jetpack Compose for UI development.
   
3. Material Design 3 for consistent styling.
   
4. ViewModel and StateFlow for managing app data.
   
5. Koin for dependency injection.

6. Retrofit for API communication.
 
7. DataStore and SharedPreferences for saving user settings.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

APIs Used:
1. Quotable API - Provides random quotes (https://github.com/lukePeavey/quotable)
   
2. Frankfurter API - Supplies currency exchange rates (https://www.frankfurter.app/)
   
3. OpenWeatherMap API - Delivers weather data (https://openweathermap.org/)
   
4. TheMealDB API - Offers random recipe information (https://www.themealdb.com/)

--------------------------------------------------------------------------------------------------------------------------------------------------------------------

How to Run the Project:
Step 1:
Download or clone the repository from GitHub.

Step 2:
Open the project in Android Studio.

Step 3:
Add your personal OpenWeatherMap API key in the WeatherRepository.kt file.

Step 4:
Build and run the app on an emulator or physical device (minimum Android SDK version 26 required).

----------------------------------------------------------------------------------------------------------------------------------------------------------------------

About the Project:
This application was developed as part of an advanced mobile technology assignment. 
It demonstrates the use of modern Android development practices including clean architecture, dependency injection, and working with external web APIs.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------
