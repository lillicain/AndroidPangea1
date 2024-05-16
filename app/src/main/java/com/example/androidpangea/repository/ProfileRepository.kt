package com.example.androidpangea.repository

import android.net.Uri
import com.example.androidpangea.models.User
import com.google.firebase.auth.FirebaseUser

interface ProfileRepository {
    val currentUser: FirebaseUser?

    suspend fun currentUserData(): User?

    fun signOut(): Resource<Boolean>

    suspend fun revokeAccess(): Resource<Boolean>

    suspend fun reloadUser(): Resource<Boolean>

    suspend fun updateUser(newDisplayName: String, email: String, phoneNumber: String) : Resource<Boolean>

    suspend fun updateProfilePhoto(newPhotoUri: Uri?) : Resource<Boolean>
}