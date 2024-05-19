package com.example.androidpangea.views.authentication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androidpangea.navigation.NavigationItem
import com.example.androidpangea.repository.EmailRepository
import java.util.Locale

class AuthViewModel(
    private val repo: EmailRepository
): ViewModel() {
    private var authState by mutableStateOf(false)
    init {
        authState = repo.getAuthState()
    }
    fun getStartDestination(): String {
        return if (authState) {
            NavigationItem.SignIn.route
        } else{
            if (isEmailVerified) {
                NavigationItem.User.route
//                TabLayoutScreen.route +"/${Locale.getDefault().displayCountry}"
            }else {
                NavigationItem.SignUp.route
//                Screen.VerifyEmailScreen.route
            }
        }
    }

    private val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false

}