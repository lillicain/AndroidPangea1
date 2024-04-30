package com.example.androidpangea.models

import android.location.Location

data class Post(
    val id: String,
    val user: String,
    val post: String,
    val description: String,
    val location: Location,
    val views: Int = 0
)
