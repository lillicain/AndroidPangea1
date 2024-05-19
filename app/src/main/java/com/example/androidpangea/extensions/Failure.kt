package com.example.androidpangea.extensions

sealed class Failure {
    data class Unknown(val error: String) : Failure()
}
