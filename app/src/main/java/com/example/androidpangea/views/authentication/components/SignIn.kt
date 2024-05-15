package com.example.androidpangea.views.authentication.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidpangea.utils.Constants.EMPTY_STRING
import com.example.androidpangea.utils.Constants.FORGOT_PASSWORD
import com.example.androidpangea.utils.Constants.NO_ACCOUNT
import com.example.androidpangea.utils.Constants.SIGN_IN_BUTTON
import com.example.androidpangea.utils.Constants.SIGN_IN_SCREEN
import com.example.androidpangea.utils.Constants.VERTICAL_DIVIDER
import com.example.androidpangea.views.authentication.EmailField
import com.example.androidpangea.views.authentication.PasswordField
import com.example.androidpangea.views.authentication.ProgressBar
import com.example.androidpangea.views.authentication.Response
import com.example.androidpangea.views.authentication.SignInViewModel
import com.example.androidpangea.views.authentication.SmallSpacer


@Composable
fun SignIn(
    viewModel: SignInViewModel = hiltViewModel(),
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    when(val signInResponse = viewModel.signInResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> Unit
        is Response.Failure -> signInResponse.apply {
            LaunchedEffect(e) {
                print(e)
                showErrorMessage(e.message)
            }
        }
    }
}

@Composable
@ExperimentalComposeUiApi
fun SignInContent(
    padding: PaddingValues,
    signIn: (email: String, password: String) -> Unit,
    navigate: Unit,
//    navigateToForgotPasswordScreen: () -> Unit,
//    navigateToSignUpScreen: () -> Unit
) {
    var email by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )
    var password by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = EMPTY_STRING
                )
            )
        }
    )
    val keyboard = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(
            email = email,
            onEmailValueChange = { newValue ->
                email = newValue
            }
        )
        SmallSpacer()
        PasswordField(
            password = password,
            onPasswordValueChange = { newValue ->
                password = newValue
            }
        )
        SmallSpacer()
        Button(
            onClick = {
                keyboard?.hide()
                signIn(email.text, password.text)
            }
        ) {
            Text(
                text = SIGN_IN_BUTTON,
                fontSize = 15.sp
            )
        }
        Row {
            Text(
                modifier = Modifier.clickable {
//                    navigateToForgotPasswordScreen()
                },
                text = FORGOT_PASSWORD,
                fontSize = 15.sp
            )
            Text(
                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                text = VERTICAL_DIVIDER,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.clickable {
//                    navigateToSignUpScreen()
                },
                text = NO_ACCOUNT,
                fontSize = 15.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInTopBar() {
    TopAppBar (
        title = {
            Text(
                text = SIGN_IN_SCREEN
            )
        }
    )
}