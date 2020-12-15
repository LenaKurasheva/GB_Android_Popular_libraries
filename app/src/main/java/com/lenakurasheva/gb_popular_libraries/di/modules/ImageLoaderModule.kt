package com.lenakurasheva.gb_popular_libraries.di.modules

import android.widget.ImageView
import com.lenakurasheva.gb_popular_libraries.mvp.model.cache.IImageCache
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.db.Database
import com.lenakurasheva.gb_popular_libraries.mvp.model.image.IImageLoader
import com.lenakurasheva.gb_popular_libraries.mvp.model.network.INetworkStatus
import com.lenakurasheva.gb_popular_libraries.ui.App
import com.lenakurasheva.gb_popular_libraries.ui.cache.room.RoomImageCache
import com.lenakurasheva.gb_popular_libraries.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Singleton

@Module
class ImageLoaderModule {

    @Singleton
    @Provides
    fun imageCache(database: Database, app: App): IImageCache = RoomImageCache(database, app)

    @Singleton
    @Provides
    fun imageLoader(imageCache: IImageCache, networkStatus: INetworkStatus, uiScheduler: Scheduler): IImageLoader<ImageView> = GlideImageLoader(imageCache, networkStatus, uiScheduler)
}