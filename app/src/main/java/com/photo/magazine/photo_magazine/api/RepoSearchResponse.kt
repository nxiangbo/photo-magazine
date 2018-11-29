package com.photo.magazine.photo_magazine.api

import com.google.gson.annotations.SerializedName
import com.photo.magazine.photo_magazine.model.Repo

data class RepoSearchResponse (
        @SerializedName("paging") val paging : RepoNext,
        @SerializedName("data") val items : List<Repo> = emptyList()
)