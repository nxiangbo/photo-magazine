package com.photo.magazine.photo_magazine.model

import android.arch.lifecycle.LiveData

data class RepoSearchResult (
        val data: LiveData<List<Repo>>,
        val networkError: LiveData<String>

)