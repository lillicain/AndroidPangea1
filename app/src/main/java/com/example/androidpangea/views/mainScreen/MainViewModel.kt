package com.example.androidpangea.views.mainScreen

import androidx.lifecycle.ViewModel
import com.example.androidpangea.extensions.BaseState
import com.example.androidpangea.extensions.Failure
import com.example.androidpangea.models.Post
import com.example.androidpangea.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val _users =
        MutableStateFlow<BaseState<List<User>, Failure>>(BaseState.Loading)
    val users = _users.asStateFlow()

    private val _posts =
        MutableStateFlow<BaseState<List<Post>, Failure>>(BaseState.Loading)
    val posts = _posts.asStateFlow()

}