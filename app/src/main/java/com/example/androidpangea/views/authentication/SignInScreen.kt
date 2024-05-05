package com.example.androidpangea.views.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun SignInScreen(
    loginViewModel: AuthViewModel? = null,
    onNavToHomePage: () -> Unit,
    onNavToSignUpPage: () -> Unit,
) {
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.loginError != null
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
                .padding(top = 10.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)


        ) {
            Text(
                "Log in",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.primary
            )

            if (isError) {
                Text(text = loginUiState?.loginError ?: "Unknown Error", color = Color.Red)
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = loginUiState?.username ?: "",
                onValueChange = { loginViewModel?.onUsernameChange(it) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(text = "Username")
                },
                isError = isError
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = loginUiState?.password ?: "",
                onValueChange = { loginViewModel?.onPasswordChange(it) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                    )
                } ,
                label = {
                    Text(text = "Password")
                },
                visualTransformation = PasswordVisualTransformation(),
                isError = isError,
            )

            Button(onClick = { loginViewModel?.loginUser(context) }) {
                Text(text = "Sign In")
            }
            Spacer(modifier = Modifier.size(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(text = "Don't have an Account?")
                Spacer(modifier = Modifier.size(8.dp))
                TextButton(onClick = { onNavToSignUpPage.invoke() }) {
                    Text(text = "Sign Up")
                }

            }

            if (loginUiState?.isLoading == true) {
                CircularProgressIndicator()
            }

            LaunchedEffect(key1 = loginViewModel?.hasUser) {
                if (loginViewModel?.hasUser == true) {
                    onNavToHomePage.invoke()
                }
            }

        }
    }
}
