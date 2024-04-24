package com.example.androidpangea.data.service

import android.app.Application
import com.example.androidpangea.R
import retrofit2.http.GET
import javax.inject.Inject

interface MyRepository {
    suspend fun doNetworkCall()
}

class MyRepositoryImpl @Inject constructor(
    private val api: MyApi,
    private val appContext: Application
): MyRepository {

    init {
        val appName = appContext.getString(R.string.app_name)
        println("Hello from the repository. The app name is $appName")
    }

    override suspend fun doNetworkCall() {

    }
}


interface MyApi {

    @GET("test")
    suspend fun doNetworkCall()
}