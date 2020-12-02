package com.lenakurasheva.gb_popular_libraries.mvp.model.api

import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface IDataSource {

    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Single<GithubUser>

//    @GET
//    fun getUserRepos(@Url url: String): Single<List<GithubRepository>>

}