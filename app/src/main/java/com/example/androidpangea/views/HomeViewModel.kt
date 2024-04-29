package com.example.androidpangea.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpangea.data.BaseState
import com.example.androidpangea.data.Failure
import com.example.androidpangea.data.service.MyRepository
import com.example.androidpangea.data.service.MyRepositoryImpl
import com.example.androidpangea.models.Post
import com.example.androidpangea.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepo: MyRepository): ViewModel() {


    private val _users = MutableStateFlow<BaseState<List<User>, Failure>>(BaseState.Loading)
    val users = _users.asStateFlow()

    private val _posts = MutableStateFlow<BaseState<List<Post>, Failure>>(BaseState.Loading)
    val posts = _posts.asStateFlow()

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            try {
                val response = homeRepo.getUserResponse()
                //                if (response.type == "success_user") {
                //                    val userList = response.data
                //
                //                    _users.value = BaseState.Success(userList)
                //                } else {
                _users.value = BaseState.Failed(Failure.Unknown("Error"))
                //                }
            } catch (e: Exception) {
                _users.value = BaseState.Failed(Failure.Unknown(e.message.toString()))
            }
        }

        fun getUserById(userId: String): User? {
            return (_users.value as BaseState.Success).data.find {
                it.id == userId
            }
        }

    }
}