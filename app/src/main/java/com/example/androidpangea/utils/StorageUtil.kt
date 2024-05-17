package com.example.androidpangea.utils

import android.content.Context
import android.net.Uri
import android.widget.Toast

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.UUID
import com.google.firebase.storage.ktx.component1
import com.google.firebase.storage.ktx.component2
import com.google.firebase.storage.StorageReference

class StorageUtil {
    companion object {

        fun uploadToStorage(uri: Uri, context: Context, type: String) {
            val storage = Firebase.storage

            val storageRef = storage.reference

            val unique_image_name = UUID.randomUUID()
            val spaceRef: StorageReference

            if (type == "image"){
                spaceRef = storageRef.child("images/$unique_image_name.jpg")
            }else{
                spaceRef = storageRef.child("videos/$unique_image_name.mp4")
            }

            val byteArray: ByteArray? = context.contentResolver
                .openInputStream(uri)
                ?.use { it.readBytes() }

            byteArray?.let{

                val uploadTask = spaceRef.putBytes(byteArray)
                uploadTask.addOnFailureListener {
                    Toast.makeText(
                        context,
                        "upload failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }.addOnSuccessListener { taskSnapshot ->
                    Toast.makeText(
                        context,
                        "success",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}