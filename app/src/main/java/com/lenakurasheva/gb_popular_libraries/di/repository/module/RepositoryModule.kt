package com.lenakurasheva.gb_popular_libraries.di.repository.module

import com.lenakurasheva.gb_popular_libraries.di.RepositoryScope
import com.lenakurasheva.gb_popular_libraries.di.UserScope
import com.lenakurasheva.gb_popular_libraries.di.repository.IRepositoryScopeContainer
import com.lenakurasheva.gb_popular_libraries.di.user.IUserScopeContainer
import com.lenakurasheva.gb_popular_libraries.mvp.model.api.IDataSource
import com.lenakurasheva.gb_popular_libraries.mvp.model.cache.IGithubUserReposCache
import com.lenakurasheva.gb_popular_libraries.mvp.model.cache.room.RoomGithubUserReposCache
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.db.Database
import com.lenakurasheva.gb_popular_libraries.mvp.model.network.INetworkStatus
import com.lenakurasheva.gb_popular_libraries.mvp.model.repo.IGithubUserReposRepo
import com.lenakurasheva.gb_popular_libraries.mvp.model.repo.RetrofitGithubUserReposRepo
import com.lenakurasheva.gb_popular_libraries.ui.App
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @RepositoryScope
    @Provides
    fun userReposCache(database: Database): IGithubUserReposCache = RoomGithubUserReposCache(database)

    @RepositoryScope
    @Provides
    fun userReposRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubUserReposCache): IGithubUserReposRepo = RetrofitGithubUserReposRepo(api, networkStatus, cache)

    @RepositoryScope
    @Provides
    fun scopeContainer(app: App): IRepositoryScopeContainer = app
}