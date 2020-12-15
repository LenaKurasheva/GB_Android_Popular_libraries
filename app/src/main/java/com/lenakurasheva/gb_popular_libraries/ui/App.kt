package com.lenakurasheva.gb_popular_libraries.ui

import android.app.Application
import com.lenakurasheva.gb_popular_libraries.di.AppComponent
import com.lenakurasheva.gb_popular_libraries.di.DaggerAppComponent
import com.lenakurasheva.gb_popular_libraries.di.modules.AppModule
import com.lenakurasheva.gb_popular_libraries.di.repository.RepositorySubcomponent
import com.lenakurasheva.gb_popular_libraries.di.user.UserSubcomponent

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var userSubcomponent: UserSubcomponent? = null
        private set

    var repositorySubcomponent: RepositorySubcomponent? = null
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent =  DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUserComponent() = appComponent.userSubcomponent().also {
        userSubcomponent = it
    }
    fun releaseUserSubcomponent(){
        userSubcomponent = null
    }

    fun initRepositoryComponent() = userSubcomponent?.repositorySubcomponent().also {
        repositorySubcomponent = it
    }
    fun releaseRepositorySubcomponent(){
        userSubcomponent = null
    }

}