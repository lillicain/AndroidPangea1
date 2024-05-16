package com.example.androidpangea.views.userScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpangea.extensions.BaseState
import com.example.androidpangea.extensions.Failure
import com.example.androidpangea.models.Post
import com.example.androidpangea.models.User
import com.example.androidpangea.views.mainScreen.getDataFromFireStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltVilillewModel
class UserViewModel: ViewModel() {

    val state = mutableStateOf(User())
    init {
        getData()
    }
    fun getData() {
        viewModelScope.launch {
            state.value = getDataFromFireStore()

        }
    }
    //    private val _users =
    //        MutableStateFlow< BaseState <List<User>, Failure>>(BaseState.Loading)
    //    val users = _users.asStateFlow()
    //
    //    private val _posts =
    //        MutableStateFlow<BaseState<List<Post>, Failure>>(BaseState.Loading)
    //    val posts = _posts.asStateFlow()
    //
    //    fun getUsers() {
    //        viewModelScope.launch {
    //            try {
    //                val response = users.value
    //                if (response.toString() == "success_user") {
    ////                    val userList = response.data.
    ////
    ////                    _users.value = BaseState.Success(userList)
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

}