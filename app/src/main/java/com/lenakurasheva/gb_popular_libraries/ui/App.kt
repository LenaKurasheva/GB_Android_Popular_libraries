package com.lenakurasheva.gb_popular_libraries.ui

import android.app.Application
import com.lenakurasheva.gb_popular_libraries.di.AppComponent
import com.lenakurasheva.gb_popular_libraries.di.DaggerAppComponent
import com.lenakurasheva.gb_popular_libraries.di.modules.AppModule

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent =  DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}