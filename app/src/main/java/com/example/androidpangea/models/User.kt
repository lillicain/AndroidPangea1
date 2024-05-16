package com.example.androidpangea.models

import com.google.api.AuthProvider
import com.google.firebase.auth.FirebaseUser

data class User(
    val id: String? = null,
    val email: String? = null,
    val username: String? = null,
    val profileImage: String? = null,
    val createdAt: String? = null,
    val authProvider: AuthProvider? = null
)

fun FirebaseUser.toUser(authProvider: AuthProvider?, createdAt: String? = null): User {
    return User(
        id = uid,
        email = email,
        username = displayName,
        profileImage = photoUrl.toString(),
        createdAt = createdAt,
        authProvider = authProvider
    )
}