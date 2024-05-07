package com.example.androidpangea.extensions

import android.app.Application
import com.example.androidpangea.views.authentication.AuthViewModel

interface DatabaseRepository {

    fun addUserDetails(viewModel: AuthViewModel, application: Application)

}