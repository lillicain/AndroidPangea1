package com.example.androidpangea.views.mainScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpangea.extensions.BaseState
import com.example.androidpangea.extensions.Failure
import com.example.androidpangea.models.Post
import com.example.androidpangea.models.User
import com.example.androidpangea.views.authentication.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: AuthRepository): ViewModel() {

    private val _users =
        MutableStateFlow< BaseState <List<User>, Failure>>(BaseState.Loading)
    val users = _users.asStateFlow()

    private val _posts =
        MutableStateFlow<BaseState<List<Post>, Failure>>(BaseState.Loading)
    val posts = _posts.asStateFlow()

    fun getUsers() {
        viewModelScope.launch {
            try {
                val response = users.value
                if (response.toString() == "success_user") {
                    //                    val userList = response.data.
                    //
                    //                    _users.value = BaseState.Success(userList)
                } else {
                    _users.value = BaseState.Failed(Failure.Unknown("Error"))
                }
            } catch (e: Exception) {
                _users.value = BaseState.Failed(Failure.Unknown(e.message.toString()))
            }
        }
    }

    fun getUserById(userId: String): User? {
        return (_users.value as BaseState.Success).data.find {
            it.id == userId
        }
    }

    fun getPostById(postId: String): Post? {
        return (_posts.value as BaseState.Success).data.find {
            it.id == postId
        }
    }
    val currentUser = repository.currentUser

    val hasUser: Boolean
        get() = repository.hasUser()

    var loginUiState by mutableStateOf(LoginUiState())
        private set

    fun onUsernameChange(username: String) {
        loginUiState = loginUiState.copy(username = username)
    }
    fun onPasswordChange(password: String) {
        loginUiState = loginUiState.copy(password = password)
    }
    fun onUsernameChangeSignUp(username: String) {
        loginUiState = loginUiState.copy(usernameSignUp = username)
    }
    fun onPasswordChangeSignUp(password: String) {
        loginUiState = loginUiState.copy(passwordSignUp = password)
    }
    fun onConfirmPasswordChange(password: String) {
        loginUiState = loginUiState.copy(confirmPasswordSignUp = password)
    }

    private fun validateLoginForm() = loginUiState.username.isBlank() && loginUiState.password.isNotBlank()


    private fun validateSignUpForm() = loginUiState.usernameSignUp.isBlank() && loginUiState.passwordSignUp.isNotBlank() && loginUiState.confirmPasswordSignUp.isNotBlank()

    fun createUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateSignUpForm()) {
                throw IllegalArgumentException("Username and Password cannot be empty.")
            }
            loginUiState = loginUiState.copy(isLoading = true)
            if (loginUiState.passwordSignUp != loginUiState.confirmPasswordSignUp) {
                throw IllegalArgumentException(
                    "Password does not match"
                )
            }
            loginUiState = loginUiState.copy(signUpError = null)
            repository.createUser(
                loginUiState.usernameSignUp,
                loginUiState.passwordSignUp
            ) { isSuccessful ->
                if (isSuccessful) {
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)

                } else {

                    Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }
        } catch (e: Exception) {
            loginUiState = loginUiState.copy(signUpError = e.localizedMessage)
            e.printStackTrace()
        } finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }

    fun loginUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateLoginForm()) {
                throw IllegalArgumentException("Username and Password cannot be empty.")
            }
            loginUiState = loginUiState.copy(isLoading = true)
            loginUiState = loginUiState.copy(loginError = null)
            repository.createUser(
                loginUiState.username,
                loginUiState.password
            ) { isSuccessful ->
                if (isSuccessful) {
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)

                } else {

                    Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }
        } catch (e: Exception) {
            loginUiState = loginUiState.copy(loginError = e.localizedMessage)
            e.printStackTrace()
        } finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }
}

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val usernameSignUp: String = "",
    val passwordSignUp: String = "",
    val confirmPasswordSignUp: String = "",
    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val signUpError: String? = null,
    val loginError: String? = null)

//    private val _users =
//        MutableStateFlow<BaseState<List<User>, Failure>>(BaseState.Loading)
//    val users = _users.asStateFlow()
//
//    private val _posts =
//        MutableStateFlow<BaseState<List<Post>, Failure>>(BaseState.Loading)
//    val posts = _posts.asStateFlow()
//
//    init {
//        getPosts()
//        getUsers()
//    }
//
//    fun getUsers() {
//        viewModelScope.launch {
//            try {
//                val response = homeRepo.getUserResponse()
//                if (response.type == "success_user") {
//                    val userList = response.data
//                    _users.value = BaseState.Success(userList)
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
//
//
//
//    fun getPosts() {
//        viewModelScope.launch {
//            _posts.value = try {
//                BaseState.Success(homeRepo.getPosts())
//            } catch (e: Exception) {
//                BaseState.Failed(Failure.Unknown(e.message.toString()))
//            }
//        }
//    }
//}