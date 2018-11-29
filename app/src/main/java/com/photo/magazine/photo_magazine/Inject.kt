package com.photo.magazine.photo_magazine

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.photo.magazine.photo_magazine.api.ZhihuService
import com.photo.magazine.photo_magazine.data.ZhihuRepository
import com.photo.magazine.photo_magazine.db.RepoDatabase
import com.photo.magazine.photo_magazine.db.ZhihuLocalDataCache
import com.photo.magazine.photo_magazine.ui.ViewModelFactory
import java.util.concurrent.Executors

object Inject {
    private fun provideCache(context: Context): ZhihuLocalDataCache {
        val database = RepoDatabase.getInstance(context)
        return ZhihuLocalDataCache(database.repos(), Executors.newSingleThreadExecutor())
    }

    private fun provideGithubRepository(context: Context): ZhihuRepository {
        return ZhihuRepository(ZhihuService.create(), provideCache(context))
    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository(context))
    }
}