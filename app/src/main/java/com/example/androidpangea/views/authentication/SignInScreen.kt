package com.example.androidpangea.views.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.androidpangea.R
import com.example.androidpangea.extensions.ButtonComponent
import com.example.androidpangea.extensions.ClickableLoginTextComponent
import com.example.androidpangea.extensions.DividerTextComponent
import com.example.androidpangea.extensions.HeadingTextComponent
import com.example.androidpangea.extensions.MyTextFieldComponent
import com.example.androidpangea.extensions.NormalTextComponent
import com.example.androidpangea.extensions.PasswordTextFieldComponent
import com.example.androidpangea.extensions.UnderLinedTextComponent
import com.example.androidpangea.navigation.NavigationItem
import com.example.androidpangea.navigation.Screen

@Composable
fun SignInScreen(
    loginViewModel: AuthViewModel = viewModel(),
//    onNavToHomePage: () -> Unit,
//    onNavToSignUpPage: () -> Unit,
    navController: NavController
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                NormalTextComponent(value = stringResource(id = R.string.signIn))
                HeadingTextComponent(value = stringResource(id = R.string.welcome))
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(labelValue = stringResource(id = R.string.username),
                    painterResource(id = R.drawable.ic_profile),
                    onTextChanged = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.ic_profile),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))
                UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.signIn),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    },
//                    isEnabled = loginViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                   AppRouter.navigateTo(Screen.SIGNUP)
                    navController.navigate(NavigationItem.SignUp.route)
                })
            }
        }

        if(loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }
    }


//    SystemBackButtonHandler {
//        AppRouter.navigateTo(Screen.SIGNUP)
//    }
}


//    val loginUiState = loginViewModel?.loginUiState
//    val isError = loginUiState?.loginError != null
//    val context = LocalContext.current
//
//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//                .padding(10.dp)
//                .padding(top = 10.dp),
//
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(10.dp)
//
//
//        ) {
//            Text(
//                "Log in",
//                style = MaterialTheme.typography.headlineMedium,
//                fontWeight = FontWeight.Black,
//                color = MaterialTheme.colorScheme.primary
//            )
//
//            if (isError) {
//                Text(text = loginUiState?.loginError ?: "Unknown Error", color = Color.Red)
//            }
//            OutlinedTextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                value = loginUiState?.username ?: "",
//                onValueChange = { loginViewModel?.onUsernameChange(it) },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Person,
//                        contentDescription = null,
//                    )
//                },
//                label = {
//                    Text(text = "Username")
//                },
//                isError = isError
//            )
//            OutlinedTextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                value = loginUiState?.password ?: "",
//                onValueChange = { loginViewModel?.onPasswordChange(it) },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Lock,
//                        contentDescription = null,
//                    )
//                } ,
//                label = {
//                    Text(text = "Password")
//                },
//                visualTransformation = PasswordVisualTransformation(),
//                isError = isError,
//            )
//
//            Button(onClick = { loginViewModel?.loginUser(context) }) {
//                Text(text = "Sign In")
//            }
//            Spacer(modifier = Modifier.size(16.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center,
//            ) {
//                Text(text = "Don't have an Account?")
//                Spacer(modifier = Modifier.size(8.dp))
//                TextButton(onClick = { onNavToSignUpPage() }) {
//                    Text(text = "Sign Up")
//                }
//
//            }
//
//            if (loginUiState?.isLoading == true) {
//                CircularProgressIndicator()
//            }
//
//            LaunchedEffect(key1 = loginViewModel?.hasUser) {
//                if (loginViewModel?.hasUser == true) {
//                    onNavToHomePage()
//                }
//            }
//
//        }
//    }
//}
