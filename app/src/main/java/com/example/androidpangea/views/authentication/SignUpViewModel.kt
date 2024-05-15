package com.example.androidpangea.views.authentication

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpangea.navigation.AppRouter
import com.example.androidpangea.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    var signInResponse by mutableStateOf<SignInResponse>(Response.Success(false))
        private set

    fun signInWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        signInResponse = Response.Loading
        signInResponse = repo.firebaseSignInWithEmailAndPassword(email, password)
    }
}

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    var signUpResponse by mutableStateOf<SignUpResponse>(Response.Success(false))
        private set
    var sendEmailVerificationResponse by mutableStateOf<SendEmailVerificationResponse>(
        Response.Success(
            false
        )
    )
        private set

    fun signUpWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        signUpResponse = Response.Loading
        signUpResponse = repo.firebaseSignUpWithEmailAndPassword(email, password)
    }

    fun sendEmailVerification() = viewModelScope.launch {
        sendEmailVerificationResponse = Response.Loading
        sendEmailVerificationResponse = repo.sendEmailVerification()
    }
}
//class SignupViewModel : ViewModel() {
//
//    private val TAG = SignupViewModel::class.simpleName
//
//
//    var registrationUIState = mutableStateOf(RegistrationUIState())
//
//    var allValidationsPassed = mutableStateOf(false)
//
//    var signUpInProgress = mutableStateOf(false)
//
//    fun onEvent(event: SignupUIEvent) {
//        when (event) {
//            is SignupUIEvent.FirstNameChanged -> {
//                registrationUIState.value = registrationUIState.value.copy(
//                    firstName = event.firstName
//                )
//                printState()
//            }
//
//            is SignupUIEvent.LastNameChanged -> {
//                registrationUIState.value = registrationUIState.value.copy(
//                    lastName = event.lastName
//                )
//                printState()
//            }
//
//            is SignupUIEvent.EmailChanged -> {
//                registrationUIState.value = registrationUIState.value.copy(
//                    email = event.email
//                )
//                printState()
//
//            }
//
//
//            is SignupUIEvent.PasswordChanged -> {
//                registrationUIState.value = registrationUIState.value.copy(
//                    password = event.password
//                )
//                printState()
//
//            }
//
//            is SignupUIEvent.RegisterButtonClicked -> {
//                signUp()
//            }
//
//            is SignupUIEvent.PrivacyPolicyCheckBoxClicked -> {
//                registrationUIState.value = registrationUIState.value.copy(
//                    privacyPolicyAccepted = event.status
//                )
//            }
//        }
//        validateDataWithRules()
//    }
//
//
//    private fun signUp() {
//        Log.d(TAG, "Inside_signUp")
//        printState()
//        createUserInFirebase(
//            email = registrationUIState.value.email,
//            password = registrationUIState.value.password
//        )
//    }
//
//    private fun validateDataWithRules() {
//        val fNameResult = Validator.validateFirstName(
//            fName = registrationUIState.value.firstName
//        )
//
//        val lNameResult = Validator.validateLastName(
//            lName = registrationUIState.value.lastName
//        )
//
//        val emailResult = Validator.validateEmail(
//            email = registrationUIState.value.email
//        )
//
//
//        val passwordResult = Validator.validatePassword(
//            password = registrationUIState.value.password
//        )
//
//        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
//            statusValue = registrationUIState.value.privacyPolicyAccepted
//        )
//
//
//        Log.d(TAG, "Inside_validateDataWithRules")
//        Log.d(TAG, "fNameResult= $fNameResult")
//        Log.d(TAG, "lNameResult= $lNameResult")
//        Log.d(TAG, "emailResult= $emailResult")
//        Log.d(TAG, "passwordResult= $passwordResult")
//        Log.d(TAG, "privacyPolicyResult= $privacyPolicyResult")
//
//        registrationUIState.value = registrationUIState.value.copy(
//            firstNameError = fNameResult.status,
//            lastNameError = lNameResult.status,
//            emailError = emailResult.status,
//            passwordError = passwordResult.status,
//            privacyPolicyError = privacyPolicyResult.status
//        )
//
//
//        allValidationsPassed.value = fNameResult.status && lNameResult.status &&
//                emailResult.status && passwordResult.status && privacyPolicyResult.status
//
//    }
//
//
//    private fun printState() {
//        Log.d(TAG, "Inside_printState")
//        Log.d(TAG, registrationUIState.value.toString())
//    }
//
//
//    private fun createUserInFirebase(email: String, password: String) {
//        signUpInProgress.value = true
//
//        FirebaseAuth
//            .getInstance()
//            .createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener {
//                Log.d(TAG, "Inside_OnCompleteListener")
//                Log.d(TAG, " isSuccessful = ${it.isSuccessful}")
//
//                signUpInProgress.value = false
//                if (it.isSuccessful) {
//                    AppRouter.navigateTo(Screen.MAIN)
//                }
//            }
//            .addOnFailureListener {
//                Log.d(TAG, "Inside_OnFailureListener")
//                Log.d(TAG, "Exception= ${it.message}")
//                Log.d(TAG, "Exception= ${it.localizedMessage}")
//            }
//    }
//
//
//}