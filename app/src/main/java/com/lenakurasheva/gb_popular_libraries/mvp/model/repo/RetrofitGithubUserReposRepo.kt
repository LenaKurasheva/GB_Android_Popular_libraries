package com.lenakurasheva.gb_popular_libraries.mvp.model.repo

import com.lenakurasheva.gb_popular_libraries.mvp.model.api.IDataSource
import com.lenakurasheva.gb_popular_libraries.mvp.model.cache.IGithubUserReposCache
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubRepository
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubUser
import com.lenakurasheva.gb_popular_libraries.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUserReposRepo(val api: IDataSource, val networkStatus: INetworkStatus, val cache: IGithubUserReposCache) : IGithubUserReposRepo {

    override fun getUserRepos(user: GithubUser) = networkStatus.inOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            user.reposUrl?.let { url ->
                api.getUserRepos(url).flatMap { repositories ->
                    cache.putUserRepos(user, repositories).andThen(Single.just(repositories))
                }
            } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url")).subscribeOn(Schedulers.io())

        } else {
            cache.getUserRepos(user)
        }
    }.subscribeOn(Schedulers.io())
}