package com.example.androidpangea.views.authentication

import java.lang.Exception

//sealed class Resource<T>(val data: T? = null, val message: String? = null) {
//    class Success<T>(data: T) : Resource<T>(data)
//    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
//    class Loading<T>(data: T? = null) : Resource<T>(data)
//}

sealed class Resource<out R> {
    data class forSuccess<out R>(val result: R): Resource<R>()
    data class forFailure(val exception: Exception): Resource<Nothing>()
    object Loading: Resource<Nothing>()
}