package com.example.androidpangea.views.authentication

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.androidpangea.R
import com.example.androidpangea.utils.AuthResultContract
import com.example.androidpangea.views.mainScreen.MainScreen
import com.example.androidpangea.views.mapScreen.MapViewModel
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.util.jar.Manifest

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


    // Load your image as ImageBitmap (replace R.drawable.your_image with your actual image resource)
    val imagePainter: Painter = painterResource(id = R.drawable.ic_profile)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Create a transparent card with rounded corners
        Card(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.25f))
                .padding(25.dp)
                .clip(RoundedCornerShape(16.dp)),
            elevation = CardDefaults.cardElevation()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // First Name TextField
                if (!isSignIn) {
                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        label = {
                            Text("First Name")
                        },
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Last Name TextField
                    TextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        label = {
                            Text("Last Name")
                        },
                    )
                }

                // Email TextField
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    label = {
                        Text("Email")
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Email, contentDescription = null)
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    ),
                    visualTransformation = if (isSignIn) VisualTransformation.None else VisualTransformation.None
                )

                // Password TextField
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    label = {
                        Text("Password")
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Lock, contentDescription = null)
                    },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password
                    ),
                    trailingIcon = {
                        IconButton(
                            onClick = { isPasswordVisible = !isPasswordVisible }
                        ) {
                            val icon = if (isPasswordVisible) Icons.Default.Lock else Icons.Default.Search
                            Icon(
                                imageVector = icon,
                                contentDescription = "Toggle Password Visibility"
                            )
                        }
                    }
                )

                // ... (other content)
                Spacer(modifier = Modifier.height(16.dp))

                // Error Message
                if (myErrorMessage != null) {
                    Text(
                        text = myErrorMessage!!,
                        color = Color.Red,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sign In/Sign Up Buttons
                Button(
                    onClick = {
                        if (isSignIn) {
                            signIn(auth = FirebaseAuth.getInstance(), email, password,
                                onSignedIn = { signedInUser ->
                                    onSignedIn(signedInUser)
                                },
                                onSignInError = { errorMessage ->
                                    // Show toast message on sign-in error
                                    myErrorMessage = errorMessage
                                }
                            )
                        } else {
                            signUp(auth = FirebaseAuth.getInstance(), email, password, firstName, lastName) { signedInUser ->
                                onSignedIn(signedInUser)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(8.dp),
                ) {
                    Text(
                        text = if (isSignIn) "Sign In" else "Sign Up",
                        fontSize = 18.sp,
                    )
                }


                // Clickable Text
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(8.dp),
                ) {
                    ClickableText(
                        text = AnnotatedString(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Blue)) {
                                append(if (isSignIn) "Don't have an account? Sign Up" else "Already have an account? Sign In")
                            }
                        }.toString()),
                        onClick = {
                            myErrorMessage = null
                            email = ""
                            password = ""
                            isSignIn = !isSignIn
                        },
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }

        }
    }
}
// Function to handle sign-in errors
private fun onSignInError(errorMessage: String) {
    // Handle the sign-in error as needed
    // For now, we'll print the error message
    println("Sign-in error: $errorMessage")
}



private fun signIn(
    auth: FirebaseAuth,
    email: String,
    password: String,
    onSignedIn: (FirebaseUser) -> Unit,
    onSignInError: (String) -> Unit
) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                onSignedIn(user!!)
            } else {
                // Handle sign-in failure
                onSignInError("Invalid email or password")
            }
        }
}


private fun signUp(
    auth: FirebaseAuth,
    email: String,
    password: String,
    firstName: String,
    lastName: String,
    onSignedIn: (FirebaseUser) -> Unit
) {
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser

                // Create a user profile in Firestore
                val userProfile = hashMapOf(
                    "firstName" to firstName,
                    "lastName" to lastName,
                    "email" to email
                )

                val firestore = FirebaseFirestore.getInstance()
                firestore.collection("users")
                    .document(user!!.uid)
                    .set(userProfile)
                    .addOnSuccessListener {
                        onSignedIn(user)
                    }
                    .addOnFailureListener {
                        //handle exception

                    }
            } else {
                // Handle sign-up failure

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
////    val user by remember(authViewModel) { authViewModel.user }.collectAsState()
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
////                                authView
////                                    email = it,
////                                    username = it1,
////
////                                    )
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
////    val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
////        if (isGranted) {
////            viewModel.getDeviceLocation(fusedLocationProviderClient)
////        }
////    }
////
////    fun askPermissions() = when {
////        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
////            viewModel.getDeviceLocation(fusedLocationProviderClient)
////        }
////        else -> {
////            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
////
////        }
////    }
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
////    user?.let {
////
////        val mapViewModel = MapViewModel()
////
////        //        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
////        askPermissions()
//
//
//
////        MainScreen(
////            state = mapViewModel.state.value,
////            setupClusterManager = mapViewModel::setupClusterManager,
////            calculateZoneViewCenter = mapViewModel::calculateZoneLatLngBounds,
////        )
//    }
//
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
////@ExperimentalAnimationApi
////@ExperimentalFoundationApi
////@ExperimentalCoroutinesApi
////@Composable
////fun AuthScreen(
////    authViewModel: AuthViewModel
////) {
////
////    val coroutineScope = rememberCoroutineScope()
////    var text by remember { mutableStateOf<String?>(null) }
////    val user by remember(authViewModel) { authViewModel.user }.collectAsState()
////    val signInRequestCode = 1
////
////    val authResultLauncher =
////        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
////            try {
////                val account = task?.getResult(ApiException::class.java)
////                if (account == null) {
////                    text = "Google sign in failed"
////                } else {
////                    coroutineScope.launch {
////                        account.email?.let {
////                            account.displayName?.let { it1 ->
////                                authViewModel.signIn(
////                                    email = it,
////                                    username = it1,
////
////                                    )
////                            }
////                        }
////                    }
////                }
////            } catch (e: ApiException) {
////                text = "Google sign in failed"
////            }
////        }
////
////
////
////    val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
////        if (isGranted) {
////            viewModel.getDeviceLocation(fusedLocationProviderClient)
////        }
////    }
////
////    fun askPermissions() = when {
////        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
////            viewModel.getDeviceLocation(fusedLocationProviderClient)
////        }
////        else -> {
////            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
////
////        }
////    }
////
////
////
////    AuthView(
////        errorText = text,
////        onClick = {
////            text = null
////            authResultLauncher.launch(signInRequestCode)
////        }
////    )
////
////    user?.let {
////
////        val mapViewModel = MapViewModel()
////
////        //        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
////        askPermissions()
////
////
////
////        MainScreen(
////            user = it,
////            state = mapViewModel.state.value,
////            setupClusterManager = mapViewModel::setupClusterManager,
////            calculateZoneViewCenter = mapViewModel::calculateZoneLatLngBounds,
////        )
////    }
////}
////
////@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
////@Composable
////fun AuthView(
////    errorText: String?,
////    onClick: () -> Unit
////) {
////    var isLoading by remember { mutableStateOf(false) }
////
////    Scaffold {
////
////        Column(
////            modifier = Modifier.fillMaxSize(),
////            verticalArrangement = Arrangement.Center,
////            horizontalAlignment = Alignment.CenterHorizontally
////        ) {
////            SignInButton(
////                text = "Sign in with Google",
////                loadingText = "Signing in...",
////                isLoading = isLoading,
////                icon = painterResource(id = R.drawable.ic_profile),
////                onClick = {
////                    isLoading = true
////                    onClick()
////                }
////            )
////
////            errorText?.let {
////                isLoading = false
////                Spacer(modifier = Modifier.height(30.dp))
////                Text(text = it)
////            }
////        }
////    }
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