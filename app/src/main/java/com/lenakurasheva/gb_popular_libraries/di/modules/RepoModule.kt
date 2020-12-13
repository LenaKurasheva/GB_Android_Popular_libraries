package com.lenakurasheva.gb_popular_libraries.di.modules

import com.lenakurasheva.gb_popular_libraries.mvp.model.api.IDataSource
import com.lenakurasheva.gb_popular_libraries.mvp.model.cache.IGithubUsersCache
import com.lenakurasheva.gb_popular_libraries.mvp.model.network.INetworkStatus
import com.lenakurasheva.gb_popular_libraries.mvp.model.repo.IGithubUsersRepo
import com.lenakurasheva.gb_popular_libraries.mvp.model.repo.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, cache: IGithubUsersCache, networkStatus: INetworkStatus): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)


//TODO userReposRepo
    
}