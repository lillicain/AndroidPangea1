package com.example.androidpangea.views.authentication

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.androidpangea.navigation.Screen

sealed class LoginUIEvent {

    data class EmailChanged(val email:String): LoginUIEvent()
    data class PasswordChanged(val password: String) : LoginUIEvent()

    object LoginButtonClicked : LoginUIEvent()
}

sealed class SignupUIEvent {
    data class FirstNameChanged(val firstName:String) : SignupUIEvent()
    data class LastNameChanged(val lastName:String) : SignupUIEvent()
    data class EmailChanged(val email:String): SignupUIEvent()
    data class PasswordChanged(val password: String) : SignupUIEvent()

    data class PrivacyPolicyCheckBoxClicked(val status:Boolean) : SignupUIEvent()

    object RegisterButtonClicked : SignupUIEvent()
}

object AppRouter {

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.SIGNUP)

    fun navigateTo(destination : Screen){
        currentScreen.value = destination
    }

}