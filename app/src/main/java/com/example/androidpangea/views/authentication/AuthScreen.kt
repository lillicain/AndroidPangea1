package com.example.androidpangea.views.authentication

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.androidpangea.R
import com.example.androidpangea.utils.AuthResultContract
import com.example.androidpangea.views.mainScreen.MainScreen
import com.example.androidpangea.views.mapScreen.MapViewModel
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.util.jar.Manifest

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@Composable
fun AuthScreen(onSignedIn: (FirebaseUser) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var isSignIn by remember { mutableStateOf(true) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    // State variables for error message
    var myErrorMessage by remember { mutableStateOf<String?>(null) }

//    authViewModel: AuthViewModel

    val coroutineScope = rememberCoroutineScope()
    var text by remember { mutableStateOf<String?>(null) }
//    val user by remember(authViewModel) { authViewModel.user }.collectAsState()
    val signInRequestCode = 1

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account == null) {
                    text = "Google sign in failed"
                } else {
                    coroutineScope.launch {
                        account.email?.let {
                            account.displayName?.let { it1 ->
//                                authView
//                                    email = it,
//                                    username = it1,
//
//                                    )
                            }
                        }
                    }
                }
            } catch (e: ApiException) {
                text = "Google sign in failed"
            }
        }



//    val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
//        if (isGranted) {
//            viewModel.getDeviceLocation(fusedLocationProviderClient)
//        }
//    }
//
//    fun askPermissions() = when {
//        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
//            viewModel.getDeviceLocation(fusedLocationProviderClient)
//        }
//        else -> {
//            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
//
//        }
//    }



    AuthView(
        errorText = text,
        onClick = {
            text = null
            authResultLauncher.launch(signInRequestCode)
        }
    )

//    user?.let {
//
//        val mapViewModel = MapViewModel()
//
//        //        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
//        askPermissions()



//        MainScreen(
//            state = mapViewModel.state.value,
//            setupClusterManager = mapViewModel::setupClusterManager,
//            calculateZoneViewCenter = mapViewModel::calculateZoneLatLngBounds,
//        )
    }


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthView(
    errorText: String?,
    onClick: () -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }

    Scaffold {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SignInButton(
                text = "Sign in with Google",
                loadingText = "Signing in...",
                isLoading = isLoading,
                icon = painterResource(id = R.drawable.ic_profile),
                onClick = {
                    isLoading = true
                    onClick()
                }
            )

            errorText?.let {
                isLoading = false
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = it)
            }
        }
    }
}
//@ExperimentalAnimationApi
//@ExperimentalFoundationApi
//@ExperimentalCoroutinesApi
//@Composable
//fun AuthScreen(
//    authViewModel: AuthViewModel
//) {
//
//    val coroutineScope = rememberCoroutineScope()
//    var text by remember { mutableStateOf<String?>(null) }
//    val user by remember(authViewModel) { authViewModel.user }.collectAsState()
//    val signInRequestCode = 1
//
//    val authResultLauncher =
//        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
//            try {
//                val account = task?.getResult(ApiException::class.java)
//                if (account == null) {
//                    text = "Google sign in failed"
//                } else {
//                    coroutineScope.launch {
//                        account.email?.let {
//                            account.displayName?.let { it1 ->
//                                authViewModel.signIn(
//                                    email = it,
//                                    username = it1,
//
//                                    )
//                            }
//                        }
//                    }
//                }
//            } catch (e: ApiException) {
//                text = "Google sign in failed"
//            }
//        }
//
//
//
//    val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
//        if (isGranted) {
//            viewModel.getDeviceLocation(fusedLocationProviderClient)
//        }
//    }
//
//    fun askPermissions() = when {
//        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
//            viewModel.getDeviceLocation(fusedLocationProviderClient)
//        }
//        else -> {
//            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
//
//        }
//    }
//
//
//
//    AuthView(
//        errorText = text,
//        onClick = {
//            text = null
//            authResultLauncher.launch(signInRequestCode)
//        }
//    )
//
//    user?.let {
//
//        val mapViewModel = MapViewModel()
//
//        //        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
//        askPermissions()
//
//
//
//        MainScreen(
//            user = it,
//            state = mapViewModel.state.value,
//            setupClusterManager = mapViewModel::setupClusterManager,
//            calculateZoneViewCenter = mapViewModel::calculateZoneLatLngBounds,
//        )
//    }
//}
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun AuthView(
//    errorText: String?,
//    onClick: () -> Unit
//) {
//    var isLoading by remember { mutableStateOf(false) }
//
//    Scaffold {
//
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            SignInButton(
//                text = "Sign in with Google",
//                loadingText = "Signing in...",
//                isLoading = isLoading,
//                icon = painterResource(id = R.drawable.ic_profile),
//                onClick = {
//                    isLoading = true
//                    onClick()
//                }
//            )
//
//            errorText?.let {
//                isLoading = false
//                Spacer(modifier = Modifier.height(30.dp))
//                Text(text = it)
//            }
//        }
//    }
//}
//import com.example.androidpangea.utils.AuthResultContract
//import com.google.android.gms.common.api.ApiException
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.launch
//
//@SuppressLint("RememberReturnType")
//@ExperimentalAnimationApi
//@ExperimentalFoundationApi
//@ExperimentalCoroutinesApi
//@Composable
//fun AuthScreen(
//    authViewModel: AuthViewModel,
////    authRepository: AuthRepository
//) {
//    val coroutineScope = rememberCoroutineScope()
//    var text by remember { mutableStateOf<String?>(null) }
//    val signInRequestCode = 1
//
//    val authResultLauncher =
//        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
//            try {
//                val account = task?.getResult(ApiException::class.java)
//                if (account == null) {
//                    text = "Google sign in failed"
//                } else {
//                    coroutineScope.launch {
////                        authViewModel.createUser()
////                            email = account.email,
//
//                    }
//                }
//            } catch (e: ApiException) {
//                text = "Google sign in failed"
//            }
//        }
//
//    AuthView(
//        errorText = text,
//        onClick = {
//            text = null
//            authResultLauncher.launch(signInRequestCode)
//        }
//    )

//    user?.let {
////        MainScreen(user = it)
//