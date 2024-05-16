package com.example.androidpangea.repository

import com.example.androidpangea.models.User
import com.example.androidpangea.views.authentication.AuthStateResponse
import com.example.androidpangea.views.authentication.ReloadUserResponse
import com.example.androidpangea.views.authentication.RevokeAccessResponse
import com.example.androidpangea.views.authentication.SendEmailVerificationResponse
import com.example.androidpangea.views.authentication.SendPasswordResetEmailResponse
import com.example.androidpangea.views.authentication.SignInResponse
import com.example.androidpangea.views.authentication.SignUpResponse
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

interface DatabaseRepository {
    val currentUser: User?
    val currentFirebaseUser: FirebaseUser?
    suspend fun firebaseSignUpWithEmailAndPassword(email: String, password: String): SignUpResponse

    suspend fun sendEmailVerification(): SendEmailVerificationResponse

    suspend fun firebaseSignInWithEmailAndPassword(email: String, password: String): SignInResponse

    suspend fun reloadFirebaseUser(): ReloadUserResponse

    suspend fun sendPasswordResetEmail(email: String): SendPasswordResetEmailResponse

    fun signOut()

    suspend fun revokeAccess(): RevokeAccessResponse

    fun getAuthState(viewModelScope: CoroutineScope): AuthStateResponse


    fun hasUser(): Boolean = currentUser != null

    fun getUserId(): String = currentUser?.id.toString()

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
    suspend fun signIn(email: String, password: String, onComplete: (Boolean) -> Unit) = withContext(
        Dispatchers.IO) {
        Firebase.auth
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onComplete.invoke(true)
                } else {
                    onComplete.invoke(false)
                }
            }.await()
    }

}

