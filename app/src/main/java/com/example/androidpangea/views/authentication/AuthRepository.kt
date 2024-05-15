package com.example.androidpangea.views.authentication

import android.annotation.SuppressLint
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

typealias SignUpResponse = Response<Boolean>
typealias SendEmailVerificationResponse = Response<Boolean>
typealias SignInResponse = Response<Boolean>
typealias ReloadUserResponse = Response<Boolean>
typealias SendPasswordResetEmailResponse = Response<Boolean>
typealias RevokeAccessResponse = Response<Boolean>
typealias AuthStateResponse = StateFlow<Boolean>

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun firebaseSignUpWithEmailAndPassword(email: String, password: String): SignUpResponse

    suspend fun sendEmailVerification(): SendEmailVerificationResponse

    suspend fun firebaseSignInWithEmailAndPassword(email: String, password: String): SignInResponse

    suspend fun reloadFirebaseUser(): ReloadUserResponse

    suspend fun sendPasswordResetEmail(email: String): SendPasswordResetEmailResponse

    fun signOut()

    suspend fun revokeAccess(): RevokeAccessResponse

    fun getAuthState(viewModelScope: CoroutineScope): AuthStateResponse
}
//interface AuthRepository {
//    val currentUser: FirebaseUser?
//    suspend fun login(email: String, password: String): Resource<FirebaseUser>
//    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
//    fun logout()
//}

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
