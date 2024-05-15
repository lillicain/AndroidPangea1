plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    //    id("com.android.application")
    //    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.androidpangea"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.androidpangea"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Core
    implementation(libs.core)

    // Lifecycle
    implementation(libs.lifecycle)
    implementation(libs.activity.compose)
    implementation(libs.lifecycle.compose)
    implementation(libs.viewmodel.compose)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.preview)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.material3)
    implementation(libs.compose.destinations)
    implementation(libs.compose.destinations.animation)
    implementation(libs.play.services.location)
    implementation(libs.appcompat)
    implementation(libs.camera.view)
    implementation(libs.browser)
    implementation(libs.firebase.firestore.ktx)
    ksp(libs.compose.ksp)
    implementation(libs.compose.extended.icons)
    implementation(libs.compose.coil)

    // Serialization
    implementation(libs.serialization)

    // Database
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.android)
    androidTestImplementation(libs.espresso)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)


    implementation("com.google.accompanist:accompanist-permissions:0.31.3-beta")

    //    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-analytics-ktx")

    //    implementation("com.google.firebase:firebase-auth-ktx")


    //    implementation("com.firebaseui:firebase-ui-auth:7.2.0")
//        implementation("com.google.android.gms:play-services-auth:19.2.0")
    //    implementation("com.facebook.android:facebook-android-sdk:8.x")
    implementation("com.google.android.gms:play-services-auth:21.1.0")

    implementation("androidx.activity:activity-compose:1.3.1")


    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("com.google.maps.android:maps-compose:2.9.0")
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.maps.android:maps-ktx:3.2.1")
    implementation("com.google.maps.android:maps-utils-ktx:3.2.1")


    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("androidx.camera:camera-core:1.0.2")
    implementation("androidx.camera:camera-camera2:1.0.2")
    implementation("androidx.camera:camera-lifecycle:1.0.2")
    implementation("androidx.camera:camera-view:1.2.0-alpha02")
    implementation("androidx.camera:camera-extensions:1.2.0-alpha02")

}
kapt {
    correctErrorTypes = true
}