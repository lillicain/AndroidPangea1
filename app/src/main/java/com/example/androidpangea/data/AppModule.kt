package com.example.androidpangea.data

import android.app.Application
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.lifecycle.ViewModel
import com.example.androidpangea.extensions.DatabaseRepository
import com.example.androidpangea.extensions.DatabaseRepositoryImpl
import com.example.androidpangea.views.authentication.AuthRepository
import com.example.androidpangea.views.authentication.AuthRepositoryImpl
import com.example.androidpangea.views.authentication.AuthViewModel
import com.example.androidpangea.views.cameraScreen.CustomCameraRepo
import com.example.androidpangea.views.cameraScreen.CustomCameraRepoImpl
import com.example.androidpangea.views.firebaseScreen.FirebaseViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesDatabaseRepository(impl: DatabaseRepositoryImpl): DatabaseRepository = impl

    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(auth = Firebase.auth)

    @Singleton
    @Provides
    fun provideFirebaseViewModel(repository: DatabaseRepository): FirebaseViewModel = FirebaseViewModel(repository)


//    @Singleton
//    @Provides
//    fun provideAuthViewModel(repository: AuthRepository): AuthViewModel = AuthViewModel(repository)

    @Provides
    @Singleton
    fun provideCameraSelector(): CameraSelector {
        return CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
    }

    @Provides
    @Singleton
    fun provideCameraProvider(application: Application): ProcessCameraProvider {
        return ProcessCameraProvider.getInstance(application).get()
    }

    @Provides
    @Singleton
    fun provideCameraPreview(): Preview {
        return Preview.Builder().build()
    }

    @Provides
    @Singleton
    fun provideImageCapture(): ImageCapture {
        return ImageCapture.Builder()
            .setFlashMode(ImageCapture.FLASH_MODE_ON)
            .setTargetAspectRatio(AspectRatio.RATIO_16_9)
            .build()
    }

    @Provides
    @Singleton
    fun provideImageAnalysis(): ImageAnalysis {
        return ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
    }

    @Provides
    @Singleton
    fun provideCustomCameraRepo(cameraProvider: ProcessCameraProvider, selector: CameraSelector, imageCapture: ImageCapture, imageAnalysis: ImageAnalysis, preview: Preview): CustomCameraRepo {
        return CustomCameraRepoImpl(cameraProvider, selector, preview, imageAnalysis, imageCapture)
    }
}