package com.photo.magazine.photo_magazine.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.photo.magazine.photo_magazine.data.ZhihuRepository
import com.photo.magazine.photo_magazine.model.Repo
import com.photo.magazine.photo_magazine.model.RepoSearchResult

class RepoSearchViewModel(private val repository: ZhihuRepository): ViewModel() {

    private val queryLiveData = MutableLiveData<String>()

    private val repoResult:LiveData<RepoSearchResult> = Transformations.map(queryLiveData, {
        repository.search(it)
    })

    val repos: LiveData<List<Repo>> = Transformations.switchMap(repoResult, {
        it -> it.data
    })

    val networkError: LiveData<String> = Transformations.switchMap(repoResult, {
        it -> it.networkError
    })

    fun searchRepo(queryString: String) {
        queryLiveData.postValue(queryString)
    }

}