package com.example.androidpangea.models

import android.location.Location

data class Post(
    val id: String,
    val userId: String,
    val postImage: String,
    val description: String,
    val location: Location,
    val views: Int = 0
)
