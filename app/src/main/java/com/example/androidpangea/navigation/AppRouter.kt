package com.example.androidpangea.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object AppRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.SIGNUP)
    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}