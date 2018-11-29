package com.photo.magazine.photo_magazine.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.photo.magazine.photo_magazine.model.Repo

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts:List<Repo>)

    @Query("SELECT * FROM zhihu_table")
    fun reposByTitle():LiveData<List<Repo>>
}