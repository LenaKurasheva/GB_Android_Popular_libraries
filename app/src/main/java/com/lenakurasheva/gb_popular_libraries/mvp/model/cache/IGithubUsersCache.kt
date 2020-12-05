package com.lenakurasheva.gb_popular_libraries.mvp.model.cache

import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubUsersCache {
    fun putUsers(users: List<GithubUser>): Completable
    fun getUsers(): Single<List<GithubUser>>
}