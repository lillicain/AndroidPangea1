package com.example.androidpangea.views.userScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidpangea.views.mainScreen.MainViewModel

@Composable
private fun UserScreenContent(
    navController: NavController,
//    navigateToForgotPasswordScreen: (email: String) -> Unit,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    viewModel: MainViewModel = hiltViewModel(),
//    navigateToCountryDetailScreen: (countryCode: String, countryName: String) -> Unit = { _, _ -> },
//    navigateToSignInScreen: () -> Unit = {},
) {
    val user = viewModel.currentUserDataResponse
    val showDialog = remember { mutableStateOf(false) }
    var email by remember(user) {
        mutableStateOf(
            TextFieldValue(user?.email ?: "")
        )
    }
    var username by remember(user) {
        mutableStateOf(
            TextFieldValue(user?.username ?: "")
        )
    }
    val coroutineScope = rememberCoroutineScope()
    if (user == null) {
        CircularProgressIndicator()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            UserUpdateFloatingActionButton(
                onClick = { showDialog.value = !showDialog.value },
                modifier = Modifier,
            )
        }
    ) { paddingValue ->
        Column(modifier = Modifier
            .padding(paddingValue)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            User(
                user = user,
//                navigateToForgotPasswordScreen = { navigateToForgotPasswordScreen(email.text) },
//                navigateToCountryDetailScreen = navigateToCountryDetailScreen,
//                navigateToSignInScreen = navigateToSignInScreen,
            )
        }

    }
    if (showDialog.value) {
        UserUpdateDialog(
            showDialog = showDialog,
            onClickConfirm = {
                viewModel.updateUser(
                    username = username.text,
                    email = email.text,
                    location = ""

                )
                showDialog.value = false
            },
            email = email,
           username = username,
            onEmailValueChange = { email = it },
            onUsernameValueChange = { username = it },
        )

    }


    RevokeAccess(
        viewModel = viewModel,
        snackBarHostState = snackBarHostState,
        coroutineScope = coroutineScope
    )

    UpdateUser(
        viewModel = viewModel,
        snackBarHostState = snackBarHostState
    )
}

@Composable
fun ProfileScreen(
    navController: NavController
//    navigateToForgotPasswordScreen: (email: String) -> Unit,

) {
    UserScreenContent(
        navController = navController
//        navigateToForgotPasswordScreen = navigateToForgotPasswordScreen,
//        snackBarHostState = snackBarHostState,

    )
}