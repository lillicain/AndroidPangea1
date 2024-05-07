package com.example.androidpangea.views.authentication

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.androidpangea.models.User
import com.example.androidpangea.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel: ViewModel() {
        private val _signInStatus = MutableStateFlow("Not Signed-in")
        val signInStatus = _signInStatus.asStateFlow()

        private val _signedInUser = MutableStateFlow<FirebaseUser?>(null)
        val signedInUser = _signedInUser.asStateFlow()

        private val _userDataFromDB = MutableStateFlow<User?>(null)
        val userDataFromDB = _userDataFromDB.asStateFlow()

        private val _userDataFlowFromDB = MutableStateFlow<User?>(null)
        val userDataFlowFromDB = _userDataFlowFromDB.asStateFlow()

        fun onSignOut() {
            _signInStatus.value = "Not Signed-in"
            _signedInUser.value = null

            _userDataFromDB.value = null
            _userDataFlowFromDB.value = null
        }

        fun onSignedIn() {
            _signInStatus.value = "Signed In"
            _signedInUser.value = FirebaseAuth.getInstance().currentUser

            val user = _signedInUser.value
            val userIdReference = Firebase.database.reference
                .child("users").child(user!!.uid)

            userIdReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val userData = dataSnapshot.getValue<User?>()
                    _userDataFlowFromDB.value = userData
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        }

        fun onSignInCancel() {
            onSignOut()
        }

        fun onSignInError(errorCode: Int?) {
            _signInStatus.value = "Failed - Error Code: $errorCode"
            _signedInUser.value = null
        }

        fun writeToDatabase() {
            val user = _signedInUser.value
            user?.run {
                val userIdReference = Firebase.database.reference
                    .child("users").child(uid)
//                val userData = dataSnapshot.getValue<User?>()
//                userIdReference.setValue(userData)
            }
        }

        fun readFromDatabase() {
            val user = _signedInUser.value
            user?.run {
                val userIdReference = Firebase.database.reference
                    .child("users").child(uid)

                userIdReference.get().addOnSuccessListener { dataSnapShot ->
                    val userData = dataSnapShot.getValue<User?>()
                    _userDataFromDB.value = userData
                }
            }
        }

    }


//    private val TAG = AuthViewModel::class.simpleName
//
//    var loginUIState = mutableStateOf(LoginUIState())
//
//    var allValidationsPassed = mutableStateOf(false)
//
//    var loginInProgress = mutableStateOf(false)
//
//
//    fun onEvent(event: LoginUIEvent) {
//        when (event) {
//            is LoginUIEvent.EmailChanged -> {
//                loginUIState.value = loginUIState.value.copy(
//                    email = event.email
//                )
//            }
//
//            is LoginUIEvent.PasswordChanged -> {
//                loginUIState.value = loginUIState.value.copy(
//                    password = event.password
//                )
//            }
//
//            is LoginUIEvent.LoginButtonClicked -> {
//                login()
//            }
//        }
//        validateLoginUIDataWithRules()
//    }
//
//    private fun validateLoginUIDataWithRules() {
//        val emailResult = Validator.validateEmail(
//            email = loginUIState.value.email
//        )
//
//
//        val passwordResult = Validator.validatePassword(
//            password = loginUIState.value.password
//        )
//
//        loginUIState.value = loginUIState.value.copy(
//            emailError = emailResult.status, passwordError = passwordResult.status
//        )
//
//        allValidationsPassed.value = emailResult.status && passwordResult.status
//
//    }
//
//    private fun login() {
//
//        loginInProgress.value = true
//        val email = loginUIState.value.email
//        val password = loginUIState.value.password
//
//        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener {
//                Log.d(TAG, "Inside_login_success")
//                Log.d(TAG, "${it.isSuccessful}")
//
//                if (it.isSuccessful) {
//                    loginInProgress.value = false
//
//                    AppRouter.navigateTo(Screen.MAIN)
//                }
//            }.addOnFailureListener {
//                Log.d(TAG, "Inside_login_failure")
//                Log.d(TAG, "${it.localizedMessage}")
//
//                loginInProgress.value = false
//
//            }
//
//    }
//}
//
//
////    private val _users = MutableStateFlow<BaseState<List<User>, Failure>>(BaseState.Loading)
////    val users = _users.asStateFlow()
////
////    private val _posts = MutableStateFlow<BaseState<List<Post>, Failure>>(BaseState.Loading)
////    val posts = _posts.asStateFlow()
////
////    //    private var _registerState = MutableStateFlow<RegisterState>(value = RegisterState())
////    //    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()
////    //
////    //    fun registerUser(email: String, password: String) = viewModelScope.launch {
////    //        repository.registerUser(email = email, password = password).collectLatest { result ->
////    //            when(result) {
////    //                is Resource.Loading -> {
////    //                    _registerState.update { it.copy(isLoading = true) }
////    //                }
////    //
////    //                is Resource.Success -> {
////    //                    _registerState.update { it.copy(isSuccess = "Register Successful!") }
////    //                }
////    //
////    //                is Resource.Error -> {
//    //                    _registerState.update { it.copy(isError = result.message) }
//    //                }
//    //            }
//    //        }
//    fun getUsers() {
//        viewModelScope.launch {
//            try {
//                val response = users.value
//                if (response.toString() == "success_user") { //                    val userList = response.data.
//                    //
//                    //                    _users.value = BaseState.Success(userList)
//                } else {
//                    _users.value = BaseState.Failed(Failure.Unknown("Error"))
//                }
//            } catch (e: Exception) {
//                _users.value = BaseState.Failed(Failure.Unknown(e.message.toString()))
//            }
//        }
//    }
//
//    fun getUserById(userId: String): User? {
//        return (_users.value as BaseState.Success).data.find {
//            it.id == userId
//        }
//    }
//
//    fun getPostById(postId: String): Post? {
//        return (_posts.value as BaseState.Success).data.find {
//            it.id == postId
//        }
//    }
//}
//
//
////        suspend fun signIn(email: String?, displayName: String?) {
////            delay(2000)
////            _username.value
////        }
////
////    val currentUser = repository.currentUser
////
////    val hasUser: Boolean
////        get() = repository.hasUser()
////
////    var loginUiState by mutableStateOf(LoginUiState())
////        private set
//
////    fun onUsernameChange(username: String) {
////        loginUiState = loginUiState.copy(username = username)
////    }
////    fun onPasswordChange(password: String) {
////        loginUiState = loginUiState.copy(password = password)
////    }
////    fun onUsernameChangeSignUp(username: String) {
////        loginUiState = loginUiState.copy(usernameSignUp = username)
////    }
////    fun onPasswordChangeSignUp(password: String) {
////        loginUiState = loginUiState.copy(passwordSignUp = password)
////    }
////    fun onConfirmPasswordChange(password: String) {
////        loginUiState = loginUiState.copy(confirmPasswordSignUp = password)
////    }
////
////    private fun validateLoginForm() = loginUiState.username.isBlank() && loginUiState.password.isNotBlank()
////
////
////    private fun validateSignUpForm() = loginUiState.usernameSignUp.isBlank() && loginUiState.passwordSignUp.isNotBlank() && loginUiState.confirmPasswordSignUp.isNotBlank()
////
////    fun createUser(context: Context) = viewModelScope.launch {
////        try {
////            if (!validateSignUpForm()) {
////                throw IllegalArgumentException("Username and Password cannot be empty.")
////            }
////            loginUiState = loginUiState.copy(isLoading = true)
////            if (loginUiState.passwordSignUp != loginUiState.confirmPasswordSignUp) {
////                throw IllegalArgumentException(
////                    "Password does not match"
////                )
////            }
////            loginUiState = loginUiState.copy(signUpError = null)
////            repository.createUser(
////                loginUiState.usernameSignUp,
////                loginUiState.passwordSignUp
////            ) { isSuccessful ->
////                if (isSuccessful) {
////                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
////                    loginUiState = loginUiState.copy(isSuccessLogin = true)
////
////                } else {
////
////                    Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
////                    loginUiState = loginUiState.copy(isSuccessLogin = false)
////                }
////            }
////        } catch (e: Exception) {
////            loginUiState = loginUiState.copy(signUpError = e.localizedMessage)
////            e.printStackTrace()
////        } finally {
////            loginUiState = loginUiState.copy(isLoading = false)
////        }
////    }
////
////    fun loginUser(context: Context) = viewModelScope.launch {
////        try {
////            if (!validateLoginForm()) {
////                throw IllegalArgumentException("Username and Password cannot be empty.")
////            }
////            loginUiState = loginUiState.copy(isLoading = true)
////            loginUiState = loginUiState.copy(loginError = null)
////            repository.createUser(
////                loginUiState.username,
////                loginUiState.password
////            ) { isSuccessful ->
////                if (isSuccessful) {
////                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
////                    loginUiState = loginUiState.copy(isSuccessLogin = true)
////
////                } else {
////
////                    Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
////                    loginUiState = loginUiState.copy(isSuccessLogin = false)
////                }
////            }
////        } catch (e: Exception) {
////            loginUiState = loginUiState.copy(loginError = e.localizedMessage)
////            e.printStackTrace()
////        } finally {
////            loginUiState = loginUiState.copy(isLoading = false)
////        }
////    }
////}