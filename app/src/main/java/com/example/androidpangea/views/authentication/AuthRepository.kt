package com.example.androidpangea.views.authentication

import android.annotation.SuppressLint
import com.example.androidpangea.utils.await
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface AuthRepository {
val currentUser: FirebaseUser?

    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId():String = Firebase.auth.currentUser?.uid.toString()

    suspend fun createUser(email: String, password: String, onComplete: (Boolean) -> Unit) = withContext(
        Dispatchers.IO) {
        Firebase.auth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if ( it.isSuccessful) {
                    onComplete.invoke(true)
                } else {
                     onComplete.invoke(false)
                }
            }.await()
    }
    @SuppressLint("RestrictedApi")
    suspend fun login(email: String, password: String): Resource<FirebaseUser>

    @SuppressLint("RestrictedApi")
    suspend fun signUp(name: String, email: String, password: String): Resource<FirebaseUser>

    fun logout()
}
