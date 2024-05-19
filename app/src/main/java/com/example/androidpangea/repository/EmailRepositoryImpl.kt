package com.example.androidpangea.repository

import android.util.Log
import com.example.androidpangea.data.database.AuthProvider
import com.example.androidpangea.utils.functions.addUserToFireStore
import com.example.androidpangea.utils.functions.getCurrentTime
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class EmailRepositoryImpl(
    private val auth: FirebaseAuth,
    private val database: FirebaseFirestore,

    ): EmailRepository {

    override val currentUser: FirebaseUser?
        get() = auth.currentUser

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<Boolean> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val isNewUser = result.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                addUserToFireStore(auth, database, AuthProvider.EMAIL, createdAt = getCurrentTime())
            }
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun sendEmailVerification(): Resource<Boolean> {
        return try {
            auth.currentUser?.sendEmailVerification()?.await()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Resource<Boolean> {
        return try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { Log.w(TAG, "Success") }
                .addOnFailureListener { Log.e(TAG, "Error", it) }.await()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun sendPasswordResetEmail(email: String): Resource<Boolean> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override fun getAuthState(): Boolean =  auth.currentUser == null
    companion object {
        private const val TAG = "emailAuthRepositoryImpl"
    }
}
