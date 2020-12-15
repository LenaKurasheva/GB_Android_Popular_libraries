package com.lenakurasheva.gb_popular_libraries.di.user.module

import com.lenakurasheva.gb_popular_libraries.di.UserScope
import com.lenakurasheva.gb_popular_libraries.mvp.model.api.IDataSource
import com.lenakurasheva.gb_popular_libraries.mvp.model.cache.IGithubUsersCache
import com.lenakurasheva.gb_popular_libraries.mvp.model.cache.room.RoomGithubUsersCache
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.db.Database
import com.lenakurasheva.gb_popular_libraries.mvp.model.network.INetworkStatus
import com.lenakurasheva.gb_popular_libraries.mvp.model.repo.IGithubUsersRepo
import com.lenakurasheva.gb_popular_libraries.mvp.model.repo.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @UserScope
    @Provides
    fun usersCache(database: Database): IGithubUsersCache = RoomGithubUsersCache(database)

    @UserScope
    @Provides
    fun usersRepo(api: IDataSource, cache: IGithubUsersCache, networkStatus: INetworkStatus): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

}