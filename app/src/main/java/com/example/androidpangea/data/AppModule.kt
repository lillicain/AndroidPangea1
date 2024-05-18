package com.example.androidpangea.data

import android.app.Application
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.example.androidpangea.models.User
import com.example.androidpangea.repository.ProfileRepository
import com.example.androidpangea.repository.ProfileRepositoryImpl
import com.example.androidpangea.views.authentication.AuthRepository
import com.example.androidpangea.views.authentication.AuthRepositoryImpl
import com.example.androidpangea.views.authentication.SignUpViewModel
import com.example.androidpangea.views.cameraScreen.CustomCameraRepo
import com.example.androidpangea.views.cameraScreen.CustomCameraRepoImpl
import com.example.androidpangea.views.mainScreen.MainViewModel
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideUser() = User()

//    @Provides
//    fun provideUserRepository(user: User): AuthRepository = AuthRepositoryImpl(user)
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(auth = Firebase.auth)

    @Provides
    fun provideProfileRepository(): ProfileRepository = ProfileRepositoryImpl(
        auth = Firebase.auth,
        db = FirebaseFirestore.getInstance(),
        storage = FirebaseStorage.getInstance()
    )

//    @Singleton
//    @Provides
//    fun provideSignInViewModel(repository: AuthRepository): SignInViewModel = SignInViewModel(repository)
//
    @Singleton
    @Provides
    fun provideMainViewModel(repository: ProfileRepository): MainViewModel = MainViewModel(repository)

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