package com.lenakurasheva.gb_popular_libraries.ui

import android.app.Application
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.db.Database
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)
    }

    val navigatorHolder
        get() = cicerone.navigatorHolder

    val router
        get() = cicerone.router
}