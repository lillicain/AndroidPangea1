package com.example.androidpangea.utils.functions

import com.example.androidpangea.data.database.AuthProvider
import com.example.androidpangea.models.toUser
import com.example.androidpangea.utils.Utils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun addUserToFireStore(
    auth: FirebaseAuth,
    database: FirebaseFirestore,
    authProvider: AuthProvider,
    createdAt: String? = null,
    isPhoneNumberVerified: Boolean? = false,
    favCountries: Map<String, String>? = null,
) {
    auth.currentUser?.apply {
        val user = toUser(authProvider = authProvider, createdAt = createdAt)
        database.collection(Utils.USERS).document(uid).set(user)
    }
}
