package com.example.androidpangea.data.service

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(
        myRepositoryImpl: MyRepositoryImpl
    ): MyRepository


    @Provides
    @Singleton
    fun provideNoteRepository(
        database: FirebaseFirestore
    ): MyRepository{
        return MyRepositoryImpl(database)
    }
}