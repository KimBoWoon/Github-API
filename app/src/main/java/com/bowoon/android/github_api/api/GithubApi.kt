package com.bowoon.android.github_api.api

import com.bowoon.android.github_api.model.RepoList
import com.bowoon.android.github_api.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Single<User>

    @GET("/users/{username}/repos")
    fun getRepoList(@Path("username") username: String): Single<RepoList>
}