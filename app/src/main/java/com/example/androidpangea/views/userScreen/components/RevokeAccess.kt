package com.example.androidpangea.views.userScreen.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.androidpangea.R
import com.example.androidpangea.repository.Resource
import com.example.androidpangea.views.authentication.ProgressBar
import com.example.androidpangea.views.mainScreen.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RevokeAccess(
    viewModel: MainViewModel,
    snackBarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
) {
    val context = LocalContext.current
    val signOut = stringResource(id= R.string.sign_out)
    fun showRevokeAccessMessage() = coroutineScope.launch {
        val result = snackBarHostState.showSnackbar(
            message = context.getString(R.string.access_revoked_message),
            actionLabel = signOut,
        )
        if (result == SnackbarResult.ActionPerformed) {
            viewModel.signOut()
        }
    }

    when (val result = viewModel.revokeAccessResponse) {
        is Resource.Loading -> ProgressBar()
        is Resource.Success -> {
            val isAccessRevoked = result.data
            LaunchedEffect(isAccessRevoked) {
                if (isAccessRevoked!!) {
//                    context.makeToast(context.getString(R.string.access_revoked_message))
                }
            }
        }

        is Resource.Error -> result.apply {
            LaunchedEffect(error) {
                if (error.message == context.toString()) {
                    showRevokeAccessMessage()
                }
            }
        }
    }
}
