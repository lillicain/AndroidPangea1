package com.example.androidpangea.repository

sealed class DataResponse<out T> {
    data class Loading(val isLoading: Boolean): DataResponse<Nothing>()
    data class Success<out T>(val data: T?): DataResponse<T>()
    data class Error(val error: Exception): DataResponse<Nothing>()
}