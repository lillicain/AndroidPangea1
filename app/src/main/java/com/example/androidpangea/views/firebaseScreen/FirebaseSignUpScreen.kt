package com.example.androidpangea.views.firebaseScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
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
import com.example.androidpangea.navigation.AppRouter
import com.example.androidpangea.navigation.NavigationItem
import com.example.androidpangea.navigation.Screen
import com.example.androidpangea.views.authentication.SignupUIEvent

@Composable
fun FirebaseSignUpScreen(viewModel: FirebaseViewModel = hiltViewModel(), navController: NavController) {
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

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.first_name),
                    painterResource(id = R.drawable.ic_profile),
                    onTextChanged = {
//                        signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                    },
//                    errorStatus = signupViewModel.registrationUIState.value.firstNameError
                )

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.last_name),
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
                        AppRouter.navigateTo(Screen.MAIN)
                    },
                    onCheckedChange = {
//                        signupViewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it))
                    }
                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.signUp),
                    onButtonClicked = {
                        //                                      signupViewModel::signUpInProgress
                        navController.navigate(NavigationItem.Main.route)
//                        signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                    },
//                    isEnabled = signupViewModel.allValidationsPassed.value,
                    navController = rememberNavController()
                )
                Button(onClick = { navController.navigate(NavigationItem.Main.route)
//                    signupViewModel::signUpInProgress
                                        }) {
                    Text("Sign Up")
                }

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                    navController.navigate(NavigationItem.SignIn.route)
                })
            }
        }

    }

//    if(signupViewModel.signUpInProgress.value) {
//        CircularProgressIndicator()
//    }
}
    //viewModel: FirebaseViewModel?) {
                         //navController: NavHostController) {

//    var name by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    val authResource = viewModel?.signupFlow?.collectAsState()
//
//    ConstraintLayout(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        val (refHeader, refName, refEmail, refPassword, refButtonSignup, refTextSignup, refLoading) = createRefs()
//        val spacing = MaterialTheme.spacing
//
//        Box(
//            modifier = Modifier
//                .constrainAs(refHeader) {
//                    top.linkTo(parent.top, spacing.extraLarge)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    width = Dimension.fillToConstraints
//                }
//                .wrapContentSize()
//        ) {
//            AuthHeader()
//        }
//
//        TextField(
//            value = name,
//            onValueChange = {
//                name = it
//            },
//            label = {
//                Text(text = stringResource(id = R.string.name))
//            },
//            modifier = Modifier.constrainAs(refName) {
//                top.linkTo(refHeader.bottom, spacing.extraLarge)
//                start.linkTo(parent.start, spacing.large)
//                end.linkTo(parent.end, spacing.large)
//                width = Dimension.fillToConstraints
//            },
//            keyboardOptions = KeyboardOptions(
//                capitalization = KeyboardCapitalization.None,
//                autoCorrect = false,
//                keyboardType = KeyboardType.Email,
//                imeAction = ImeAction.Next
//            )
//        )
//
//        TextField(
//            value = email,
//            onValueChange = {
//                email = it
//            },
//            label = {
//                Text(text = stringResource(id = R.string.email))
//            },
//            modifier = Modifier.constrainAs(refEmail) {
//                top.linkTo(refName.bottom, spacing.medium)
//                start.linkTo(parent.start, spacing.large)
//                end.linkTo(parent.end, spacing.large)
//                width = Dimension.fillToConstraints
//            },
//            keyboardOptions = KeyboardOptions(
//                capitalization = KeyboardCapitalization.None,
//                autoCorrect = false,
//                keyboardType = KeyboardType.Email,
//                imeAction = ImeAction.Next
//            )
//        )
//
//        TextField(
//            value = password,
//            onValueChange = {
//                password = it
//            },
//            label = {
//                Text(text = stringResource(id = R.string.password))
//            },
//            modifier = Modifier.constrainAs(refPassword) {
//                top.linkTo(refEmail.bottom, spacing.medium)
//                start.linkTo(parent.start, spacing.large)
//                end.linkTo(parent.end, spacing.large)
//                width = Dimension.fillToConstraints
//            },
//            visualTransformation = PasswordVisualTransformation(),
//            keyboardOptions = KeyboardOptions(
//                capitalization = KeyboardCapitalization.None,
//                autoCorrect = false,
//                keyboardType = KeyboardType.Password,
//                imeAction = ImeAction.Done
//            )
//        )
//
//        Button(
//            onClick = {
//                viewModel?.signupUser(name, email, password)
//            },
//            modifier = Modifier.constrainAs(refButtonSignup) {
//                top.linkTo(refPassword.bottom, spacing.large)
//                start.linkTo(parent.start, spacing.extraLarge)
//                end.linkTo(parent.end, spacing.extraLarge)
//                width = Dimension.fillToConstraints
//            }
//        ) {
//            Text(text = stringResource(id = R.string.signup), style = MaterialTheme.typography.titleMedium)
//        }
//
//
//        Text(
//            modifier = Modifier
//                .constrainAs(refTextSignup) {
//                    top.linkTo(refButtonSignup.bottom, spacing.medium)
//                    start.linkTo(parent.start, spacing.extraLarge)
//                    end.linkTo(parent.end, spacing.extraLarge)
//                }
//                .clickable {
//                    navController.navigate(NavigationItem.FirebaseSignIn.route) {
//                        popUpTo(NavigationItem.FirebaseSignUp.route) { inclusive = true }
//                    }
//                },
//            text = stringResource(id = R.string.already_have_account),
//            style = MaterialTheme.typography.bodyLarge,
//            textAlign = TextAlign.Center,
//            color = MaterialTheme.colorScheme.onSurface
//        )
//
//        authResource?.value?.let {
//            when (it) {
//                is Resource.Failure -> {
//                    ShowToast(message = it.exception.message.toString())
//                }
//                is Resource.Loading -> {
//                    CircularProgressIndicator(modifier = Modifier.constrainAs(refLoading) {
//                        top.linkTo(parent.top)
//                        bottom.linkTo(parent.bottom)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                    })
//                }
//                is Resource.Success -> {
//                    LaunchedEffect(Unit) {
//                        navController.navigate(ROUTE_HOME) {
//                            popUpTo(ROUTE_SIGNUP) { inclusive = true }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}