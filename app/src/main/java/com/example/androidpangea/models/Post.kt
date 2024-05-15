package com.example.androidpangea.models

import android.location.Location

data class Post(
    val id: String,
    val user: String,
    val image: String,
    val description: String,
    val location: Location,
)
