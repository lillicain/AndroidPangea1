package com.example.androidpangea.extensions

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import com.example.androidpangea.models.User
import com.example.androidpangea.views.authentication.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth): DatabaseRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): FirebaseResource<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            FirebaseResource.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            FirebaseResource.Failure(e)
        }
    }

    override suspend fun signup(name: String, email: String, password: String): FirebaseResource<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
            return FirebaseResource.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            FirebaseResource.Failure(e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}