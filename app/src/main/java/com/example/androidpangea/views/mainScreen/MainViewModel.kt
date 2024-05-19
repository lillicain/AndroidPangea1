package com.example.androidpangea.views.mainScreen

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpangea.extensions.BaseState
import com.example.androidpangea.extensions.Failure
import com.example.androidpangea.extensions.await
import com.example.androidpangea.models.Post
import com.example.androidpangea.models.User
import com.example.androidpangea.repository.ProfileRepository
import com.example.androidpangea.repository.Resource
import com.example.androidpangea.views.authentication.AuthRepository
import com.example.androidpangea.views.mapScreen.MapItem
import com.example.androidpangea.views.mapScreen.MapState
import com.example.androidpangea.views.mapScreen.MapViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.toObject
import com.google.maps.android.ktx.model.polygonOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ProfileRepository): ViewModel() {
    private val _users = MutableStateFlow<BaseState<List<User>, Failure>>(BaseState.Loading)
    val users = _users.asStateFlow()

    private val _posts = MutableStateFlow<BaseState<List<Post>, Failure>>(BaseState.Loading)
    val posts = _posts.asStateFlow()

    val state = mutableStateOf(User())



    var revokeAccessResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))

    var reloadUserResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))

    var updateUserResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))

    var currentUserDataResponse by mutableStateOf<User?>(null)

    val currentUser get() = repository.currentUser

    private val mapState: MutableState<MapState> = mutableStateOf(
        MapState(
            lastKnownLocation = null,
            mapItems = listOf(
                MapItem(
                    id = "1",
                    title = "School",
                    snippet = "This is Zone 1.",
                    polygonOptions = polygonOptions {
                        //                        add(LatLng(37.09, 113.57))
                        //                        add(LatLng(37.098, 113.58))
                        //                        add(LatLng(37.094, 113.591))
                        //                        add(LatLng(37.01, 113.59))

                        add(LatLng(39.105, -122.524))
                        add(LatLng(39.101, -122.529))
                        add(LatLng(39.092, -122.501))
                        add(LatLng(39.1, -122.506))

                        fillColor(MapViewModel.POLYGON_FILL_COLOR)
                    }
                ),
                MapItem(
                    id = "2",
                    title = "Test",
                    snippet = "This is a test area.",
                    polygonOptions = polygonOptions {

                        add(LatLng(39.105, -122.524))
                        add(LatLng(39.101, -122.529))
                        add(LatLng(39.092, -122.501))
                        add(LatLng(39.1, -122.506))

                        //                        add(LatLng(37.11, 113.36))
                        //                        add(LatLng(37.123, 113.373))
                        //                        add(LatLng(37.111, 113.37))

                        fillColor(MapViewModel.POLYGON_FILL_COLOR)
                    }
                )
            )
        )
    )

    init {
        getData()
        getUserData()
    }
    fun getUserData() = viewModelScope.launch {
        currentUserDataResponse = repository.currentUserData()
    }
    fun reloadUser() = viewModelScope.launch {
        reloadUserResponse = Resource.Loading

        reloadUserResponse = repository.reloadUser()
    }

    val isEmailVerified get() = repository.currentUser?.isEmailVerified ?: false

    fun signOut() = repository.signOut()

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = Resource.Loading

        revokeAccessResponse = repository.revokeAccess()
    }

    fun updateUser(username: String, email: String, location: String?) = viewModelScope.launch {
        updateUserResponse = Resource.Loading

        updateUserResponse = repository.updateUser(username, email, location)
    }

    fun updateProfilePhoto(newPhotoUri: Uri) = viewModelScope.launch {
        updateUserResponse = Resource.Loading

        updateUserResponse = repository.updateProfilePhoto(newPhotoUri)
    }
    fun getData() {
        viewModelScope.launch {
            state.value = getDataFromFireStore()

        }
    }

    fun getUsers() {
        viewModelScope.launch {
            try {
                val response = users.value
                if (response.toString() == "user") {
//                                        val userList = response.d
                    //
                    //                                        _users.value = BaseState.Success(userList)
                } else {
                    _users.value = BaseState.Failed(Failure.Unknown("error"))
                }
            } catch (e: Exception) {
                _users.value = BaseState.Failed(Failure.Unknown(e.message.toString()))
            }
        }
    }

    fun getUserById(userId: String): User? {
        return (_users.value as BaseState.Success).data.find {
            it.id == userId
        }
    }

    fun getPostById(postId: String): Post? {
        return (_posts.value as BaseState.Success).data.find {
            it.id == postId
        }
    }


        fun getPosts() {
            viewModelScope.launch {
    //            _posts.value = try {
    ////                BaseState.Success(repository.getPosts())
    //            } catch (e: Exception) {
    //                BaseState.Failed(Failure.Unknown(e.message.toString()))
    //            }
            }
        }
    @SuppressLint("MissingPermission")
    fun getDeviceLocation(
        fusedLocationProviderClient: FusedLocationProviderClient
    ) {
        try {
            val locationResult = fusedLocationProviderClient.lastLocation
            locationResult.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mapState.value = mapState.value.copy(
                        lastKnownLocation = task.result,
                    )
                }
            }
        } catch (_: SecurityException) {

        }
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