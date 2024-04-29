package com.example.androidpangea.data.service

import android.app.Application
import com.example.androidpangea.R
import com.example.androidpangea.data.firebase.FireStoreTables
import com.example.androidpangea.models.Post
import com.example.androidpangea.models.User
import com.google.firebase.firestore.FirebaseFirestore
import retrofit2.http.GET
import javax.inject.Inject

interface MyRepository {
    suspend fun doNetworkCall()

    val userList: List<User>
        get() = mutableListOf<User>()

    suspend fun getUserResponse() {

    }

    fun getPosts(result: (UiState<List<Post>>) -> Unit)
    fun addPost(post: Post, result: (UiState<String>) -> Unit)
    fun updatePost(post: Post, result: (UiState<String>) -> Unit)

}

class MyRepositoryImpl @Inject constructor(
    //    private val api: MyApi,
    //    private val appContext: Application
    val database: FirebaseFirestore
): MyRepository {

    init {
        val appName = "Pangea"
        println("Hello from the repository. The app name is $appName")
    }

    override suspend fun doNetworkCall() {

    }

    override fun getPosts(result: (UiState<List<Post>>) -> Unit) {
        database.collection(FireStoreTables.POST).get().addOnSuccessListener {
            val notes = arrayListOf<Post>()
            for (document in it) {
                val note = document.toObject(Post::class.java)
                notes.add(note)
            }
            result.invoke(
                UiState.Success(notes)
            )
        }.addOnFailureListener {
            result.invoke(
                UiState.Failure(
                    it.localizedMessage
                )
            )
        }
    }

    override fun addPost(post: Post, result: (UiState<String>) -> Unit) {
        val document = database.collection(FireStoreTables.POST).document()
        post.id = document.id
        document.set(post).addOnSuccessListener {
            result.invoke(
                UiState.Success("Note has been created successfully")
            )
        }.addOnFailureListener {
            result.invoke(
                UiState.Failure(
                    it.localizedMessage
                )
            )
        }
    }

    override fun updatePost(post: Post, result: (UiState<String>) -> Unit) {
        val document = database.collection(FireStoreTables.POST).document(post.id)
        document.set(post).addOnSuccessListener {
            result.invoke(
                UiState.Success("Note has been update successfully")
            )
        }.addOnFailureListener {
            result.invoke(
                UiState.Failure(
                    it.localizedMessage
                )
            )
        }
    }
}


interface MyApi {

    @GET("test")
    suspend fun doNetworkCall()
}