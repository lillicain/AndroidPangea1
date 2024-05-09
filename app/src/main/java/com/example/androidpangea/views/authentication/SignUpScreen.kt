package com.example.androidpangea.views.authentication

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.androidpangea.R
import com.example.androidpangea.extensions.ButtonComponent
import com.example.androidpangea.extensions.CheckboxComponent
import com.example.androidpangea.extensions.ClickableLoginTextComponent
import com.example.androidpangea.extensions.DividerTextComponent
import com.example.androidpangea.extensions.HeadingTextComponent
import com.example.androidpangea.extensions.MyTextFieldComponent
import com.example.androidpangea.extensions.NormalTextComponent
import com.example.androidpangea.extensions.PasswordTextFieldComponent
import com.example.androidpangea.navigation.NavigationItem

@Composable
fun SignUpScreen(
    viewModel: AuthViewModel?,
    //    onNavToHomePage:() -> Unit,
    //    onNavToLoginPage:() -> Unit,
    navController: NavHostController
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val authResource = viewModel?.loginFlow?.collectAsState()
//    val signupFlow = viewModel?.signupFlow?.collectAsState()

    //    val auth: FirebaseAuth by lazy { Firebase.auth }
    //    val appState = rememberAppState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                NormalTextComponent(value = stringResource(id = R.string.welcome))
                HeadingTextComponent(value = stringResource(id = R.string.create_account))
                Spacer(modifier = Modifier.height(20.dp))

                //                MyTextFieldComponent(
                //                    labelValue = stringResource(id = R.string.first_name),
                //                    painterResource(id = R.drawable.ic_profile),
                //                    onTextChanged = {
                ////                        signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                //                    },
                ////                    errorStatus = signupViewModel.registrationUIState.value.firstNameError
                //                )

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.name),
                    painterResource = painterResource(id = R.drawable.ic_profile),
                    onTextChanged = {
                        //                        signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                    },
                    //                    errorStatus = signupViewModel.registrationUIState.value.lastNameError
                )

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.username),
                    painterResource = painterResource(id = R.drawable.ic_profile),
                    onTextChanged = {
                        //                        signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                    },
                    //                    errorStatus = signupViewModel.registrationUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource = painterResource(id = R.drawable.ic_profile),
                    onTextSelected = {
                        //                        signupViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                    },
                    //                    errorStatus = signupViewModel.registrationUIState.value.passwordError
                )

                CheckboxComponent(value = stringResource(id = R.string.terms_and_conditions),
                    onTextSelected = {
                        //                        AppRouter.navigateTo(Screen.MAIN)
                    },
                    onCheckedChange = {
                        //                        signupViewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it))
                    }
                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.signUp),
                    onButtonClicked = {
                        viewModel?.signupUser(name, email, password)
                        //                                      signupViewModel::signUpInProgress
                        //                        navController.navigate(NavigationItem.Main.route)
                        //                        signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                    },
                    //                    isEnabled = signupViewModel.allValidationsPassed.value,
                    navController = rememberNavController()
                )
                Button(onClick = { viewModel?.signupUser(name, email, password)}) {

                    //                    navController.navigate(NavigationItem.Main.route)
                    //                    signupViewModel::signUpInProgress}) {
                    Text("Sign Up")
                }

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                    navController.navigate(NavigationItem.SignIn.route)
                })
            }
        }



        //    if(signupViewModel.signUpInProgress.value) {
        //        CircularProgressIndicator()
        //    }
        //}
        authResource?.value?.let {
            when (it) {
                is Resource.forFailure -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }
                Resource.Loading -> {
//                    CircularProgressIndicator



                }

                is Resource.forSuccess<*> -> {
                    LaunchedEffect(Unit) {

                        navController.navigate(NavigationItem.Main.route) {
                            popUpTo(NavigationItem.Main.route) { inclusive = true }
                        }
                    }
                }
            }
        }
    }
}

//}

//    var loginUiState = loginViewModel?.loginUiState
//    val isError = loginUiState?.signUpError != null
//    val context = LocalContext.current
//var text = ""
//
//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Text(
//                text = "Sign Up",
//                style = MaterialTheme.typography.headlineMedium,
//                fontWeight = FontWeight.Black,
//                color = MaterialTheme.colorScheme.primary
//            )
//
//            if (isError) {
//                Text(
//                    text = loginUiState?.signUpError ?: "unknown error",
//                    color = Color.Red,
//                )
//            }
//
//            OutlinedTextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                value = text,
//                onValueChange = { loginViewModel?.onPasswordChangeSignUp(it) },
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
//
//            )
//
//            OutlinedTextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                value = text,//loginUiState?.passwordSignUp ?: "",
//                onValueChange = { loginViewModel?.onPasswordChangeSignUp(text) },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Lock,
//                        contentDescription = null,
//                    )
//                },
//                label = {
//                    Text(text = "Password")
//                },
//                visualTransformation = PasswordVisualTransformation(),
//                isError = isError
//            )
//            OutlinedTextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                value = text,
//                onValueChange = { loginViewModel?.onConfirmPasswordChange(text) },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Lock,
//                        contentDescription = null,
//                    )
//                },
//                label = {
//                    Text(text = "Confirm Password")
//                },
//                visualTransformation = PasswordVisualTransformation(),
//                isError = isError
//            )
//
//            Button(onClick = { onNavToHomePage()
//                loginViewModel?.createUser(context)
//            navController.navigate(NavigationItem.Main.route)
//            }) {
//                Text(text = "Sign In")
//            }
//
//            Spacer(modifier = Modifier.size(16.dp))
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center,
//            ) {
//                Text(text = "Already have an Account?")
//                Spacer(modifier = Modifier.size(8.dp))
//                TextButton(onClick = { onNavToLoginPage() }) {
//                    Text(text = "Sign In")
//                }
//            }
//
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
//        }
//    }
//}
//
