package com.example.androidpangea.views.authentication.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidpangea.utils.Constants.ALREADY_USER
import com.example.androidpangea.utils.Constants.EMPTY_STRING
import com.example.androidpangea.utils.Constants.SIGN_UP_BUTTON
import com.example.androidpangea.utils.Constants.SIGN_UP_SCREEN
import com.example.androidpangea.views.authentication.BackIcon
import com.example.androidpangea.views.authentication.EmailField
import com.example.androidpangea.views.authentication.PasswordField
import com.example.androidpangea.views.authentication.Response
import com.example.androidpangea.views.authentication.SignUpViewModel
import com.example.androidpangea.views.authentication.SmallSpacer
import com.example.androidpangea.views.authentication.ProgressBar


@Composable
fun SendEmailVerification(
    viewModel: SignUpViewModel = hiltViewModel()
) {
    when(val sendEmailVerificationResponse = viewModel.sendEmailVerificationResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> Unit
        is Response.Failure -> sendEmailVerificationResponse.apply {
            LaunchedEffect(e) {
                print(e)
            }
        }
    }
}

@Composable
fun SignUp(
    viewModel: SignUpViewModel = hiltViewModel(),
    sendEmailVerification: () -> Unit,
    showVerifyEmailMessage: () -> Unit
) {
    when(val signUpResponse = viewModel.signUpResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
            val isUserSignedUp = signUpResponse.data
            LaunchedEffect(isUserSignedUp) {
                if (isUserSignedUp) {
                    sendEmailVerification()
                    showVerifyEmailMessage()
                }
            }
        }
        is Response.Failure -> signUpResponse.apply {
            LaunchedEffect(e) {
                print(e)
            }
        }
    }
}

@Composable
@ExperimentalComposeUiApi
fun SignUpContent(
    padding: PaddingValues,
    signUp: (email: String, password: String) -> Unit,
//    navigateBack: () -> Unit
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
                signUp(email.text, password.text)
            }
        ) {
            Text(
                text = SIGN_UP_BUTTON,
                fontSize = 15.sp
            )
        }
        Text(
            modifier = Modifier.clickable {
//                navigateBack()
            },
            text = ALREADY_USER,
            fontSize = 15.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpTopBar(
    navigateBack: Unit
) {
    TopAppBar (
        title = {
            Text(
                text = SIGN_UP_SCREEN
            )
        },
        navigationIcon = {
//            BackIcon(
////                navigateBack = navigateBack
//            )
        }
    )
}