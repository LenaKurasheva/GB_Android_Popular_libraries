package com.lenakurasheva.gb_popular_libraries.di.user

import com.lenakurasheva.gb_popular_libraries.di.UserScope
import com.lenakurasheva.gb_popular_libraries.di.repository.RepositorySubcomponent
import com.lenakurasheva.gb_popular_libraries.di.user.module.UserModule
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.UsersPresenter
import com.lenakurasheva.gb_popular_libraries.ui.adapter.UsersRvAdapter
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = [UserModule::class])
interface UserSubcomponent {
    fun repositorySubcomponent(): RepositorySubcomponent

    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRvAdapter: UsersRvAdapter)
}