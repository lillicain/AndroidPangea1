package com.example.androidpangea.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import com.example.androidpangea.utils.Constants.TAG

class Utils {
    companion object {
        const val USERS = "Users"
        const val TAG = "TAG"
        fun printLog(e: Exception) = Log.e(TAG, e.stackTraceToString())

        fun Context.makeToast(message: String?) =
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        fun print(e: Exception) = Log.e(TAG, e.stackTraceToString())

        fun showMessage(
            context: Context,
            message: String?
        ) = makeText(context, message, LENGTH_LONG).show()
    }
}