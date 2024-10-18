package com.example.hcahealthcaretask.service

import com.example.hcahealthcaretask.model.RepositoryDataItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {
    @GET("users/{username}/repos")
    fun getRepositories(
        @Path("username") username: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Single<List<RepositoryDataItem>>
}

