package com.example.androidpangea

import android.app.Application
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext

@HiltAndroidApp
class MainApplication: Application() {
}