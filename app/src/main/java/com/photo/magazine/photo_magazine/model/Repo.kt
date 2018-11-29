package com.photo.magazine.photo_magazine.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "zhihu_table")
data class Repo (
        @PrimaryKey @field:SerializedName("id") val id : Long,
        @field:SerializedName("title") val title : String,
        @field:SerializedName("excerpt") val excerpt : String?,
        @field:SerializedName("url") val url : String
)
