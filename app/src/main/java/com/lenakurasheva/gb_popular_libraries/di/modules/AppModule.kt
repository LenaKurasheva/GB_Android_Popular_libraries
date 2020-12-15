package com.lenakurasheva.gb_popular_libraries.di.modules

import androidx.room.Room
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.db.Database
import com.lenakurasheva.gb_popular_libraries.ui.App
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Provides
    fun app(): App{
        return app
    }
}