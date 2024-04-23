package com.example.androidpangea.data

data class BaseResponse<T>(
    val type: String,
    val data: List<T>,
)