package com.example.androidpangea.models

import android.os.Parcelable
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize
import java.util.Date

//data class Post(
//    val id: String,
//    val userId: String,
//    val postImage: String
//)

@Parcelize
data class Post(
    var id: String = "",
    val text: String = "",
    @ServerTimestamp
    val date: Date = Date(),
): Parcelable