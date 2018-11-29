package com.photo.magazine.photo_magazine.api

import android.util.Log
import com.photo.magazine.photo_magazine.model.Repo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val TAG = "GithubService"

fun searchRepos(
        service: ZhihuService,
        query:String,
        onSuccess:(repos: List<Repo>) -> Unit,
        onError: (error: String) -> Unit
) {
    service.searchRepos(query).enqueue(
            object : Callback<RepoSearchResponse>{
                override fun onFailure(call: Call<RepoSearchResponse>?, t: Throwable?) {
                    Log.d(TAG, "fail to get data")
                    onError(t!!.message ?: "unknown error")
                }

                override fun onResponse(call: Call<RepoSearchResponse>?, response: Response<RepoSearchResponse>?) {
                    Log.d(TAG, "got a response $response")

                    if (response != null) {
                        if (response.isSuccessful){
                            val repos = response.body()?.items?: emptyList()
                            val next = response.body()?.paging?: null
                            Log.d(TAG, next.toString())
                            onSuccess(repos)
                        } else {
                            Log.d(TAG, "fail to get data")
                            onError("unknown error")
                        }
                    }
                }

            })
}


interface ZhihuService {
    @GET("/search?type=content")
    fun searchRepos(@retrofit2.http.Query("q")  content: String) : Call<RepoSearchResponse>

    companion object {
        private const val BASE_URL = "https://api.zhihu.com/"

        fun create(): ZhihuService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder().addInterceptor(logger).build()

            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ZhihuService::class.java)

        }
    }
}