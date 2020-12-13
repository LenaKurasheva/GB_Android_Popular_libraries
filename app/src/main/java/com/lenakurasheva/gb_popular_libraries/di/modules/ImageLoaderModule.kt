package com.lenakurasheva.gb_popular_libraries.di.modules

import android.widget.ImageView
import com.lenakurasheva.gb_popular_libraries.mvp.model.cache.IImageCache
import com.lenakurasheva.gb_popular_libraries.mvp.model.image.IImageLoader
import com.lenakurasheva.gb_popular_libraries.mvp.model.network.INetworkStatus
import com.lenakurasheva.gb_popular_libraries.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Singleton

@Module
class ImageLoaderModule {

    @Singleton
    @Provides
    fun glideImageLoader(imageCache: IImageCache, networkStatus: INetworkStatus, uiScheduler: Scheduler): IImageLoader<ImageView> = GlideImageLoader(imageCache, networkStatus, uiScheduler)
}