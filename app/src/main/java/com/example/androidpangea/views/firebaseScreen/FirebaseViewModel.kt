package com.example.androidpangea.views.firebaseScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpangea.extensions.DatabaseRepository
import com.example.androidpangea.extensions.FirebaseResource
import com.example.androidpangea.views.authentication.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FirebaseViewModel @Inject constructor(
    private val repository: DatabaseRepository
) : ViewModel() {

    private val _loginFlow = MutableStateFlow<FirebaseResource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<FirebaseResource<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<FirebaseResource<FirebaseUser>?>(null)
    val signupFlow: StateFlow<FirebaseResource<FirebaseUser>?> = _signupFlow

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init {
        if (repository.currentUser != null) {
            _loginFlow.value = FirebaseResource.Success(repository.currentUser!!)
        }
    }

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        _loginFlow.value = FirebaseResource.Loading
        val result = repository.login(email, password)
        _loginFlow.value = result
    }

    fun signupUser(name: String, email: String, password: String) = viewModelScope.launch {
        _signupFlow.value = FirebaseResource.Loading
        val result = repository.signup(name, email, password)
        _signupFlow.value = result
    }

    fun logout() {
        repository.logout()
        _loginFlow.value = null
        _signupFlow.value = null
    }
}
