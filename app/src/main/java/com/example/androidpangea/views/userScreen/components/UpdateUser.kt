package com.example.androidpangea.views.userScreen.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.androidpangea.R
import com.example.androidpangea.repository.Resource
import com.example.androidpangea.views.authentication.ProgressBar
import com.example.androidpangea.views.mainScreen.MainViewModel

@Composable
fun UpdateUser(
    viewModel: MainViewModel,
    snackBarHostState: SnackbarHostState
) {
    val context = LocalContext.current
    when (val result = viewModel.updateUserResponse) {
        is Resource.Loading -> ProgressBar()
        is Resource.Success -> {
            val isUserUpdated = result.data
            LaunchedEffect(isUserUpdated) {
                if (isUserUpdated!!) {
                    viewModel.getUserData()
                    snackBarHostState.showSnackbar(context.getString(R.string.user_successfully_update_message))
                }
            }
        }

        is Resource.Error -> result.apply {
            LaunchedEffect(error) {
//                printLog(error)
                snackBarHostState.showSnackbar(context.getString(R.string.user_update_error_message) + error.localizedMessage)
            }
        }
    }
}