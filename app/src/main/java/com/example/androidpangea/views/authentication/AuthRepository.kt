package com.example.androidpangea.views.authentication

import android.annotation.SuppressLint
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}

//interface AuthRepository {
//val currentUser: FirebaseUser?
//
//    fun hasUser(): Boolean = Firebase.auth.currentUser != null
//
//    fun getUserId():String = Firebase.auth.currentUser?.uid.toString()
//
//    suspend fun createUser(email: String, password: String, onComplete: (Boolean) -> Unit) = withContext(Dispatchers.IO) {
//        Firebase.auth
//            .createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener {
//                if ( it.isSuccessful) {
//                    onComplete.invoke(true)
//                } else {
//                     onComplete.invoke(false)
//                }
//            }.await()
//    }
//    suspend fun login(email: String, password: String, onComplete: (Boolean) -> Unit) = withContext(Dispatchers.IO) {
//        Firebase.auth
//            .signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener {
//                if ( it.isSuccessful) {
//                    onComplete.invoke(true)
//                } else {
//                    onComplete.invoke(false)
//                }
//            }.await()
//    }

//}

//interface AuthRepository {
//    @SuppressLint("RestrictedApi")
//    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
//
//    @SuppressLint("RestrictedApi")
//    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>
//
//}
