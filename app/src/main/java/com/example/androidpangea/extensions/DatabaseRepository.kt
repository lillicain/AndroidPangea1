package com.example.androidpangea.extensions

import android.app.Application
import com.example.androidpangea.views.authentication.AuthViewModel
import com.google.firebase.auth.FirebaseUser

interface DatabaseRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()

}