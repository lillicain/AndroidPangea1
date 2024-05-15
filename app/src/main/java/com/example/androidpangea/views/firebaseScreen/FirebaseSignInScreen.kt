package com.example.androidpangea.views.firebaseScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidpangea.R
import com.example.androidpangea.extensions.ButtonComponent
import com.example.androidpangea.extensions.ClickableLoginTextComponent
import com.example.androidpangea.extensions.DividerTextComponent
import com.example.androidpangea.extensions.HeadingTextComponent
import com.example.androidpangea.extensions.MyTextFieldComponent
import com.example.androidpangea.extensions.NormalTextComponent
import com.example.androidpangea.extensions.PasswordTextFieldComponent
import com.example.androidpangea.extensions.UnderLinedTextComponent
import com.example.androidpangea.navigation.AppRouter
import com.example.androidpangea.navigation.NavigationItem
import com.example.androidpangea.navigation.Screen

@Composable
fun FirebaseSignInScreen(viewModel: FirebaseViewModel = hiltViewModel(), navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
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
//                        viewModel::onEmailChange
                        //                        viewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    //                    errorStatus = viewModel.loginUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.ic_profile),
                    onTextSelected = {
                        //                        viewModel.onEvent(LoginUIEvent.PasswordChanged(it))
//                        viewModel::allValidationsPassed
                    },
                    //                    errorStatus = viewModel.loginUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))
                UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

                Spacer(modifier = Modifier.height(40.dp))


                ButtonComponent(
                    value = stringResource(id = R.string.signIn),
                    onButtonClicked = {
//                        navController.navigate(NavigationItem.Main.route)
//                        viewModel::loginInProgress
                        //                        viewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    },
//                    isEnabled = viewModel.allValidationsPassed.value,
                    navController = rememberNavController()

                )
                Button(onClick = {
//                    navController.navigate(NavigationItem.Main.route)
//                    viewModel::loginInProgress

                }) {
                    Text("Sign In")
                }

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    AppRouter.navigateTo(Screen.SIGNUP)
//                    navController.navigate(NavigationItem.SignUp.route)
                })
            }
            //            AuthView(
            //                errorText = text,
            //                onClick = {
            //                    navController.navigate(NavigationItem.Main.route)
            //                    text = null
            //                    authResultLauncher.launch(signInRequestCode)
            //                }
            //            )
        }


    }




}