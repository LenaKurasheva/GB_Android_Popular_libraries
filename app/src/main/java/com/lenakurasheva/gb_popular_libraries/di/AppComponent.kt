package com.lenakurasheva.gb_popular_libraries.di

import com.lenakurasheva.gb_popular_libraries.di.modules.*
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.CurrentUserPresenter
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.MainPresenter
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.RepositoryPresenter
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.UsersPresenter
import com.lenakurasheva.gb_popular_libraries.ui.activity.MainActivity
import com.lenakurasheva.gb_popular_libraries.ui.adapter.UsersRvAdapter
import com.lenakurasheva.gb_popular_libraries.ui.fragment.CurrentUserFragment
import com.lenakurasheva.gb_popular_libraries.ui.fragment.RepositoryFragment
import com.lenakurasheva.gb_popular_libraries.ui.fragment.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ApiModule::class,
    NavigationModule::class,
    CacheModule::class,
    RepoModule::class,
    ImageLoaderModule::class
])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(currentUserPresenter: CurrentUserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
    fun inject(usersRvAdapter: UsersRvAdapter)
}