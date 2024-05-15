package com.example.androidpangea.views.authentication

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidpangea.navigation.NavigationItem
import com.example.androidpangea.utils.Constants.VERIFY_EMAIL_MESSAGE
import com.example.androidpangea.utils.Utils.Companion.showMessage
import com.example.androidpangea.views.authentication.components.SendEmailVerification
import com.example.androidpangea.views.authentication.components.SignUp
import com.example.androidpangea.views.authentication.components.SignUpContent
import com.example.androidpangea.views.authentication.components.SignUpTopBar

@Composable
@ExperimentalComposeUiApi
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController
//    navigateBack: () -> Unit
) {
    val context = LocalContext.current

//    Scaffold(
//        topBar = {
//            SignUpTopBar(
//                navigateBack = navController.navigate(NavigationItem.SignIn.route)
////                navigateBack = navigateBack
//            )
//        },
//        content = { padding ->
            SignUpContent(

                signUp = { email, password ->
                    viewModel.signUpWithEmailAndPassword(email, password)
                },
                navController = navController
            )
//        }
//    )

    SignUp(
        sendEmailVerification = {
            viewModel.sendEmailVerification()
        },
        showVerifyEmailMessage = {
            showMessage(context, VERIFY_EMAIL_MESSAGE)
        }
    )

    SendEmailVerification()
}
//@Composable
//fun SignUpScreen(viewModel: SignUpViewModel = hiltViewModel(), navController: NavController) {
//
////    val auth: FirebaseAuth by lazy { Firebase.auth }
////    val appState = rememberAppState()
//
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//
//        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(30.dp)
//        ) {
//            Column(modifier = Modifier.fillMaxSize()) {
//
//                NormalTextComponent(value = stringResource(id = R.string.welcome))
//                HeadingTextComponent(value = stringResource(id = R.string.create_account))
//                Spacer(modifier = Modifier.height(20.dp))
//
//                MyTextFieldComponent(
//                    labelValue = stringResource(id = R.string.first_name),
//                    painterResource(id = R.drawable.ic_profile),
//                    onTextChanged = {
////                        signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
//                    },
////                    errorStatus = signupViewModel.registrationUIState.value.firstNameError
//                )
//
//                MyTextFieldComponent(
//                    labelValue = stringResource(id = R.string.last_name),
//                    painterResource = painterResource(id = R.drawable.ic_profile),
//                    onTextChanged = {
////                        signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
//                    },
////                    errorStatus = signupViewModel.registrationUIState.value.lastNameError
//                )
//
//                MyTextFieldComponent(
//                    labelValue = stringResource(id = R.string.username),
//                    painterResource = painterResource(id = R.drawable.ic_profile),
//                    onTextChanged = {
////                        signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
//                    },
////                    errorStatus = signupViewModel.registrationUIState.value.emailError
//                )
//
//                PasswordTextFieldComponent(
//                    labelValue = stringResource(id = R.string.password),
//                    painterResource = painterResource(id = R.drawable.ic_profile),
//                    onTextSelected = {
////                        signupViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
//                    },
////                    errorStatus = signupViewModel.registrationUIState.value.passwordError
//                )
//
//                CheckboxComponent(value = stringResource(id = R.string.terms_and_conditions),
//                    onTextSelected = {
//                        AppRouter.navigateTo(Screen.MAIN)
//                    },
//                    onCheckedChange = {
////                        signupViewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it))
//                    }
//                )
//
//                Spacer(modifier = Modifier.height(40.dp))
//
//                ButtonComponent(
//                    value = stringResource(id = R.string.signUp),
//                    onButtonClicked = {
////                                      signupViewModel::signUpInProgress
//                        navController.navigate(NavigationItem.Main.route)
//
////                        signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
//                    },
////                    isEnabled = signupViewModel.allValidationsPassed.value,
//                    navController = rememberNavController()
//                )
//                Button(onClick = { navController.navigate(NavigationItem.Main.route)
////                    signupViewModel::signUpInProgress
//
//                }) {
//                    Text("Sign Up")
//                }
//
//                Spacer(modifier = Modifier.height(20.dp))
//
//                DividerTextComponent()
//
//                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
//                    navController.navigate(NavigationItem.SignIn.route)
//                })
//            }
//        }
//
//    }
//
////    if(signupViewModel.signUpInProgress.value) {
////        CircularProgressIndicator()
////    }
//}


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
