package com.example.androidpangea.repository

sealed class Resource<out T> {
    data object Loading: Resource<Nothing>()
    data class Success<out T>(val data: T?): Resource<T>()
    data class Error(val error: Exception): Resource<Nothing>()
}


