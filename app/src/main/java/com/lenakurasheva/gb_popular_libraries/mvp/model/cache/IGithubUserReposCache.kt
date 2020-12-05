package com.lenakurasheva.gb_popular_libraries.mvp.model.cache

import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubRepository
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubUserReposCache {
    fun putUserRepos (user: GithubUser, repositories: List<GithubRepository>): Completable
    fun getUserRepos (user: GithubUser): Single<List<GithubRepository>>
}