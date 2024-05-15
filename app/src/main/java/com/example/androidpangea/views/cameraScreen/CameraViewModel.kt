package com.example.androidpangea.views.cameraScreen

import android.content.Context
import android.graphics.Bitmap
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpangea.views.subviews.SavePhotoToGalleryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CameraViewModel: ViewModel() {

    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    fun onTakePhoto(bitmap: Bitmap) {
        _bitmaps.value += bitmap
    }
}
