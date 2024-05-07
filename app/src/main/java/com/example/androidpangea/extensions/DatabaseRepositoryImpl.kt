package com.example.androidpangea.extensions

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import com.example.androidpangea.models.User
import com.example.androidpangea.views.authentication.AuthViewModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class DatabaseRepositoryImpl : DatabaseRepository {
    @SuppressLint("SuspiciousIndentation")
    override fun addUserDetails(viewModel: AuthViewModel, application: Application) {



        val dB: FirebaseFirestore = FirebaseFirestore.getInstance()
        val dbUsers: CollectionReference = dB.collection("Users")

        val users = User("", "", "")

        dbUsers.add(users).addOnSuccessListener {
            Toast.makeText(application, "User added successfully!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { e ->
            Toast.makeText(application, "Exception: $e", Toast.LENGTH_SHORT).show()
        }


    }
}