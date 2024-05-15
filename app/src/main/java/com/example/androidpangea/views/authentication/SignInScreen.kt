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

    Scaffold(
        topBar = {
            SignInTopBar()
        },
        content = { padding ->
            SignInContent(
                padding = padding,
                signIn = { email, password ->
                    viewModel.signInWithEmailAndPassword(email, password)
                },
               navController =  navController
            )
        }
    )

    SignIn(
        showErrorMessage = { errorMessage ->
            showMessage(context, errorMessage)
        }
    )
}
//@Composable
//fun SignInScreen(viewModel: AuthViewModel = hiltViewModel(), navController: NavController) {
//
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    val uiState by viewModel.uiState
////    val auth: FirebaseAuth by lazy { Firebase.auth }
//    val coroutineScope = rememberCoroutineScope()
//    var text by remember { mutableStateOf<String?>(null) }
//    val signInRequestCode = 1
//
//    val authResultLauncher = rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
//        try {
//            val account = task?.getResult(ApiException::class.java)
//            if (account == null) {
//                text = "Google sign in failed"
//            } else {
//                coroutineScope.launch {
//                    account.email?.let {
//                        account.displayName?.let { it1 ->
//
//
//
//
//                            //                                authView
//                            //                                    email = it,
//                            //                                    username = it1,
//                            //
//                            //                                    )
//                        }
//                    }
//                }
//            }
//        } catch (e: ApiException) {
//            text = "Google sign in failed"
//        }
//    }
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
//
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//            ) {
//
//                NormalTextComponent(value = stringResource(id = R.string.signIn))
//                HeadingTextComponent(value = stringResource(id = R.string.welcome))
//                Spacer(modifier = Modifier.height(20.dp))
//
//                MyTextFieldComponent(labelValue = stringResource(id = R.string.username),
//                    painterResource(id = R.drawable.ic_profile),
//                    onTextChanged = {
//                        viewModel::onEmailChange
//                        //                        viewModel.onEvent(LoginUIEvent.EmailChanged(it))
//                    },
//                    //                    errorStatus = viewModel.loginUIState.value.emailError
//                )
//
//                PasswordTextFieldComponent(
//                    labelValue = stringResource(id = R.string.password),
//                    painterResource(id = R.drawable.ic_profile),
//                    onTextSelected = {
//                        //                        viewModel.onEvent(LoginUIEvent.PasswordChanged(it))
//                        viewModel::allValidationsPassed
//                    },
//                    //                    errorStatus = viewModel.loginUIState.value.passwordError
//                )
//
//                Spacer(modifier = Modifier.height(40.dp))
//                UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))
//
//                Spacer(modifier = Modifier.height(40.dp))
//
//
//                ButtonComponent(
//                    value = stringResource(id = R.string.signIn),
//                    onButtonClicked = {
//                        navController.navigate(NavigationItem.Main.route)
////                        viewModel.loginUser(email = email, password = password)
////                        viewModel::loginInProgress
//                        //                        viewModel.onEvent(LoginUIEvent.LoginButtonClicked)
//                    },
//                    isEnabled = viewModel.allValidationsPassed.value,
//                    navController = rememberNavController()
//
//                )
//                Button(onClick = {
//                    navController.navigate(NavigationItem.Main.route)
//                    viewModel.loginInProgress
//
//                }) {
//                    Text("Sign In")
//                }
//
//                Spacer(modifier = Modifier.height(20.dp))
//
//                DividerTextComponent()
//
//                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
//                    AppRouter.navigateTo(Screen.SIGNUP)
//                    navController.navigate(NavigationItem.SignUp.route)
//                })
//            }
//            //            AuthView(
//            //                errorText = text,
//            //                onClick = {
//            //                    navController.navigate(NavigationItem.Main.route)
//            //                    text = null
//            //                    authResultLauncher.launch(signInRequestCode)
//            //                }
//            //            )
//        }
//
//        if(viewModel.loginInProgress.value) {
//            CircularProgressIndicator()
//        }
//    }
//
//
//    //    SystemBackButtonHandler {
//    //        AppRouter.navigateTo(Screen.SIGNUP)
//    //    }
//}
//
//@Composable
//fun EmailField(value: String,  onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
//    OutlinedTextField(
//        singleLine = true,
//        modifier = modifier,
//        value = value,
//        onValueChange = { onNewValue(it) },
//        placeholder = { "" },
//        leadingIcon = {  }
//    )
//}
//
//interface AccountService {
//    fun createAnonymousAccount(onResult: (Throwable?) -> Unit)
//    fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit)
//    fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit)
//}
//
////    val loginUiState = loginViewModel?.loginUiState
////    val isError = loginUiState?.loginError != null
////    val context = LocalContext.current
////
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
