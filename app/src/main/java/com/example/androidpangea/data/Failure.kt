package com.example.androidpangea.data

sealed class Failure {
    data class Unknown(val error: String) : Failure()
}
