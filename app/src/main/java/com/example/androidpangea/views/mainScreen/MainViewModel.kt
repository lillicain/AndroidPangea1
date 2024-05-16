package com.example.androidpangea.views.mainScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpangea.extensions.BaseState
import com.example.androidpangea.extensions.Failure
import com.example.androidpangea.extensions.await
import com.example.androidpangea.models.Post
import com.example.androidpangea.models.User
import com.example.androidpangea.views.authentication.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    val state = mutableStateOf(User())

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
state.value = getDataFromFireStore()

        }
    }
//    private val _users = MutableStateFlow<BaseState<List<User>, Failure>>(BaseState.Loading)
//    val users = _users.asStateFlow()
//
//    private val _posts = MutableStateFlow<BaseState<List<Post>, Failure>>(BaseState.Loading)
//    val posts = _posts.asStateFlow()
//
//    fun getUsers() {
//        viewModelScope.launch {
//            try {
//                val response = users.value
//                if (response.toString() == "success_user") {
//                    //                    val userList = response.data.
//                    //
////                                        _users.value = BaseState.Success(userList)
//                } else {
//                    _users.value = BaseState.Failed(Failure.Unknown("Error"))
//                }
//            } catch (e: Exception) {
//                _users.value = BaseState.Failed(Failure.Unknown(e.message.toString()))
//            }
//        }
//    }
//
//    fun getUserById(userId: String): User? {
//        return (_users.value as BaseState.Success).data.find {
//            it.id == userId
//        }
//    }
//
//    fun getPostById(postId: String): Post? {
//        return (_posts.value as BaseState.Success).data.find {
//            it.id == postId
//        }
//    }
//    fun getPosts() {
//        viewModelScope.launch {
////            _posts.value = try {
//////                BaseState.Success(repository.getPosts())
////            } catch (e: Exception) {
////                BaseState.Failed(Failure.Unknown(e.message.toString()))
////            }
//        }
//    }
}

suspend fun getDataFromFireStore(): User {
    val db = FirebaseFirestore.getInstance()
var user = User()

    try {
        db.collection("users").get().await().map {
           val result = it.toObject(User::class.java)
            user = result
        }
    } catch (e: FirebaseFirestoreException) {
        Log.d("error", "getDataFromFireStore: $e")

    }

    return user
}