package com.example.androidpangea.views.authentication.firebase

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.androidpangea.views.authentication.AuthViewModel
import com.example.androidpangea.views.mainScreen.MainViewModel
import com.firebase.ui.auth.AuthUI

//class FirebaseScreen {
//val viewModel: AuthViewModel
//
//var showSignIn by remember {mutableStateOf(false)}
//
//val signInStatus by viewModel.signInStatus.collectAsStateWithLifecycle()
//val signedInUser by viewModel.signedInUser.collectAsStateWithLifecycle()
//val userDataFromDB by viewModel.userDataFromDB.collectAsStateWithLifecycle()
//val userDataFlowFromDB by viewModel.userDataFlowFromDB.collectAsStateWithLifecycle()
//
//val context = LocalContext.current
//
//Column(
//modifier = Modifier.fillMaxSize(),
//verticalArrangement = Arrangement.Center,
//horizontalAlignment = Alignment.CenterHorizontally,
//) {
//
//    Spacer(Modifier.padding(2.dp))
//    Text("Sign-in Status: $signInStatus")
//
//    Spacer(Modifier.padding(2.dp))
//    Text("User Id: ${signedInUser?.uid}")
//
//    Spacer(Modifier.padding(2.dp))
//    Button(onClick = {
//        showSignIn = true
//    }) {
//        Text("Sign In")
//    }
//
//    Spacer(Modifier.padding(2.dp))
//    Button(onClick = {
//        AuthUI.getInstance().signOut(context)
//        viewModel.onSignOut()
//    }) {
//        Text("Sign Out")
//    }
//
//    Spacer(Modifier.padding(2.dp))
//    Button(onClick = {
//        viewModel.writeToDatabase()
//    }) {
//        Text("Write Data")
//    }
//
//    Spacer(Modifier.padding(2.dp))
//    Button(onClick = {
//        viewModel.readFromDatabase()
//    }) {
//        Text("Read Data")
//    }
//
//}
//
//if (showSignIn) {
//    SignInScreen { result ->
//        // (4) Handle the sign-in result callback
//        if (result.resultCode == ComponentActivity.RESULT_OK) {
//            viewModel.onSignedIn()
//        } else {
//            val response = result.idpResponse
//            if (response == null) {
//                viewModel.onSignInCancel()
//            } else {
//                val errorCode = response.getError()?.getErrorCode()
//                viewModel.onSignInError(errorCode)
//            }
//        }
//
//        showSignIn = false
//    }
//}
//}
