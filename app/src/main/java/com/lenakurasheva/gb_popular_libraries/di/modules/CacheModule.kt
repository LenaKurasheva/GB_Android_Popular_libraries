package com.lenakurasheva.gb_popular_libraries.di.modules

import androidx.room.Room
import com.lenakurasheva.gb_popular_libraries.mvp.model.cache.IGithubUsersCache
import com.lenakurasheva.gb_popular_libraries.mvp.model.cache.IImageCache
import com.lenakurasheva.gb_popular_libraries.mvp.model.cache.room.RoomGithubUsersCache
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.db.Database
import com.lenakurasheva.gb_popular_libraries.ui.App
import com.lenakurasheva.gb_popular_libraries.ui.cache.room.RoomImageCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IGithubUsersCache = RoomGithubUsersCache(database)

    @Singleton
    @Provides
    fun imageCache(database: Database, app: App): IImageCache = RoomImageCache(database, app)

    //TODO currentUserCache

}