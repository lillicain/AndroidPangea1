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

//@HiltViewModel
class CameraViewModel(
    private val savePhotoToGalleryUseCase: SavePhotoToGalleryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CameraState())
    val state = _state.asStateFlow()

    fun storePhotoInGallery(bitmap: Bitmap) {
        viewModelScope.launch {
            savePhotoToGalleryUseCase.call(bitmap)
            updateCapturedPhotoState(bitmap)
        }
    }

    private fun updateCapturedPhotoState(updatedPhoto: Bitmap?) {
        _state.value.capturedImage?.recycle()
        _state.value = _state.value.copy(capturedImage = updatedPhoto)
    }

    override fun onCleared() {
        _state.value.capturedImage?.recycle()
        super.onCleared()
    }
}

data class CameraState(
    val capturedImage: Bitmap? = null,
)


//class CameraViewModel(
//    private val savePhotoToGalleryUseCase: SavePhotoToGalleryUseCase,
//    private val repo: CustomCameraRepo
//) : ViewModel() {
//
//    private val _state = MutableStateFlow(com.example.androidpangea.views.subviews.CameraState())
//    val state = _state.asStateFlow()
//
//    fun showCameraPreview(
//        previewView: PreviewView,
//        lifecycleOwner: LifecycleOwner
//    ){
//        viewModelScope.launch {
//            repo.showCameraPreview(
//                previewView,
//                lifecycleOwner
//            )
//        }
//    }
//
//    fun captureAndSave(context: Context){
//        viewModelScope.launch {
//            repo.captureAndSaveImage(context)
//        }
//    }
//
//    fun storePhotoInGallery(bitmap: Bitmap) {
//        viewModelScope.launch {
//            savePhotoToGalleryUseCase.call(bitmap)
//            updateCapturedPhotoState(bitmap)
//        }
//    }
//
//    private fun updateCapturedPhotoState(updatedPhoto: Bitmap?) {
//        _state.value.capturedImage?.recycle()
//        _state.value = _state.value.copy(capturedImage = updatedPhoto)
//    }
//
//    override fun onCleared() {
//        _state.value.capturedImage?.recycle()
//        super.onCleared()
//    }
//}

