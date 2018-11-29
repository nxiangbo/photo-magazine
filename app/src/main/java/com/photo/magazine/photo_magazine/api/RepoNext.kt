package com.photo.magazine.photo_magazine.api

import com.google.gson.annotations.SerializedName

data class RepoNext(
        @field:SerializedName("is_end") val isEnd: Boolean,
        @field:SerializedName("next") val next: String
)