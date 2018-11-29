package com.photo.magazine.photo_magazine.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.photo.magazine.photo_magazine.data.ZhihuRepository

class ViewModelFactory(private val repository: ZhihuRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepoSearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RepoSearchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}