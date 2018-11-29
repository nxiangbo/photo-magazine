package com.photo.magazine.photo_magazine.db

import android.arch.lifecycle.LiveData
import com.photo.magazine.photo_magazine.model.Repo
import java.util.concurrent.Executor

class ZhihuLocalDataCache(private val repoDao: RepoDao, private val ioExecutor: Executor) {

    fun insert(repos: List<Repo>, insertFinished: () ->Unit) {
        ioExecutor.execute{
            repoDao.insert(repos)
            insertFinished()
        }
    }

    fun reposByName(name: String): LiveData<List<Repo>> {
        // appending '%' so we can allow other characters to be before and after the query string
        val query = "%${name.replace(' ', '%')}%"
        return repoDao.reposByTitle()
    }
}
