package com.example.androidpangea.views

import androidx.lifecycle.ViewModel
import com.example.androidpangea.data.service.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: Lazy<MyRepository>
): ViewModel() {

    init {
//        repository.get()
    }
}