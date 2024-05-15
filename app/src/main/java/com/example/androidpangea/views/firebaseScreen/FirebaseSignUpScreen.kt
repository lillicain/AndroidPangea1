package com.example.androidpangea.views.firebaseScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import com.example.androidpangea.R
import com.example.androidpangea.navigation.NavigationItem

@Composable
fun FirebaseSignUpScreen() {
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
}