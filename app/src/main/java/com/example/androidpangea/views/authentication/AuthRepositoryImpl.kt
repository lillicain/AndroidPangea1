package com.example.androidpangea.views.authentication

import android.annotation.SuppressLint
import com.example.androidpangea.utils.await
import com.firebase.ui.auth.data.model.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth): AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    @SuppressLint("RestrictedApi")
    override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
        try {

            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
//            Resource.Success(result.user!!)
            Resource.forSuccess(result.user!!)
        } catch (e: Exception) {
//            Resource.Failure(e)
            Resource.forFailure(e)

        }
    }

    @SuppressLint("RestrictedApi")
    override suspend fun signUp(
        name: String, email: String, password: String
    ): Resource<FirebaseUser> {
        try {

            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build()).await()
                        Resource.Success(result.user!!)
//            Resource.forSuccess(result.user!!)
        } catch (e: Exception) {
            Resource.Failure(e)
//            Resource.forFailure(e)


        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}