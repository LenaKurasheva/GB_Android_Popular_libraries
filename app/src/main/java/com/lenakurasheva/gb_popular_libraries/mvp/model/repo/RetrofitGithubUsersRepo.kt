package com.lenakurasheva.gb_popular_libraries.mvp.model.repo

import com.lenakurasheva.gb_popular_libraries.mvp.model.api.IDataSource
import com.lenakurasheva.gb_popular_libraries.mvp.model.cache.IGithubUsersCache
import com.lenakurasheva.gb_popular_libraries.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource, val networkStatus: INetworkStatus, val cache: IGithubUsersCache) : IGithubUsersRepo {

    override fun getUsers() = networkStatus.inOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                cache.putUsers(users).andThen(Single.just(users))
            }
        } else {
            cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())

}