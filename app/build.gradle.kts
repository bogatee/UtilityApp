plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.utilityapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.utilityapp"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0") //2.8.7
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.10.0") //2.8.7
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.10.0") //2.8.7
    implementation ("com.squareup.retrofit2:retrofit:3.0.0") //2.11.0
    implementation ("com.squareup.retrofit2:converter-scalars:3.0.0") //2.11.0
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2") //1.8.1


   // new for assessment 1

    // Koin for Dependency Injection
    implementation("io.insert-koin:koin-android:3.5.3")
    implementation("io.insert-koin:koin-androidx-compose:3.5.3")

    // Gson converter for Retrofit (replace scalars)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // DataStore for settings
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Navigation Compose (for multiple screens)
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Extended icons
    implementation("androidx.compose.material:material-icons-extended:1.6.0")

    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")


}