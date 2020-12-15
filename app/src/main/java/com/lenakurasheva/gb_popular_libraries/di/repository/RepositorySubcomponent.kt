package com.lenakurasheva.gb_popular_libraries.di.repository

import com.lenakurasheva.gb_popular_libraries.di.RepositoryScope
import com.lenakurasheva.gb_popular_libraries.di.repository.module.RepositoryModule
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.CurrentUserPresenter
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.RepositoryPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent( modules = [RepositoryModule::class])

interface RepositorySubcomponent {
    fun inject(currentUserPresenter: CurrentUserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}