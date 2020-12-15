package com.lenakurasheva.gb_popular_libraries.di

import com.lenakurasheva.gb_popular_libraries.di.modules.*
import com.lenakurasheva.gb_popular_libraries.di.user.UserSubcomponent
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.MainPresenter
import com.lenakurasheva.gb_popular_libraries.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ApiModule::class,
    NavigationModule::class,
    ImageLoaderModule::class
])
interface AppComponent {
    fun userSubcomponent(): UserSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}