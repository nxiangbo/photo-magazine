package com.photo.magazine.photo_magazine.data

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.photo.magazine.photo_magazine.api.ZhihuService
import com.photo.magazine.photo_magazine.api.searchRepos
import com.photo.magazine.photo_magazine.db.ZhihuLocalDataCache
import com.photo.magazine.photo_magazine.model.RepoSearchResult

class ZhihuRepository(private val service: ZhihuService,
                      private val cache: ZhihuLocalDataCache) {

    private var requestInProgress = false
    private val netWorkError = MutableLiveData<String>()

    fun search (query: String): RepoSearchResult {
        requestAndSaveData(query)
        val data = cache.reposByName(query)
        return RepoSearchResult(data, netWorkError)
    }

    fun requestMore(query: String) {
        requestAndSaveData(query)
    }

    private fun requestAndSaveData(query: String) {
        if (requestInProgress) return

        requestInProgress = true
        searchRepos(service, query, { repos ->
            cache.insert(repos, {
                requestInProgress = false
            })
        }, { error ->
            netWorkError.postValue(error)
            requestInProgress = false

        })
    }
}