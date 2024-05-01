package com.example.androidpangea.firebase.repository

import android.app.Activity
import com.example.androidpangea.firebase.ResultState
import com.example.androidpangea.models.User
import com.example.androidpangea.navigation.NavigationItem
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun createUser(
        auth: User
    ) : Flow<ResultState<String>>

    fun loginUser(
        auth: NavigationItem.User
    ) : Flow<ResultState<String>>

    fun createUserWithPhone(
        phone:String,
        activity: Activity
    ) : Flow<ResultState<String>>

    fun signWithCredential(
        otp:String
    ): Flow<ResultState<String>>

}