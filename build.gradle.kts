// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.serialization).apply(false)
    alias(libs.plugins.ksp).apply(false)
    alias(libs.plugins.kapt).apply(false)
    alias(libs.plugins.hilt).apply(false)
    id("com.google.gms.google-services") version "4.4.1" apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
}