package com.example.androidpangea.views.authentication

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidpangea.navigation.NavigationItem
import com.example.androidpangea.utils.Utils.Companion.showMessage
import com.example.androidpangea.views.authentication.components.SignIn
import com.example.androidpangea.views.authentication.components.SignInContent
import com.example.androidpangea.views.authentication.components.SignInTopBar


@Composable
@ExperimentalComposeUiApi
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current

            SignInContent(
                signIn = { email, password ->
                    viewModel.signInWithEmailAndPassword(email, password)
                },
               navController =  navController
            )

    SignIn(
        showErrorMessage = { errorMessage ->
            showMessage(context, errorMessage)
        }
    )
}