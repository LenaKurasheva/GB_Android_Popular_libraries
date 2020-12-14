package com.lenakurasheva.gb_popular_libraries.mvp.presenter

import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubUser
import com.lenakurasheva.gb_popular_libraries.mvp.model.repo.IGithubUsersRepo
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.list.IUsersListPresenter
import com.lenakurasheva.gb_popular_libraries.mvp.view.UsersView
import com.lenakurasheva.gb_popular_libraries.mvp.view.list.UserItemView
import com.lenakurasheva.gb_popular_libraries.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class UsersPresenter() : MvpPresenter<UsersView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var usersRepoRetrofit: IGithubUsersRepo
    @Inject lateinit var uiScheduler: Scheduler

    class UsersListPresenter : IUsersListPresenter {
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        val users = mutableListOf<GithubUser>()

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            user.avatarUrl?.let { view.loadImage(it) }
        }
        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()
    var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { view ->
            router.navigateTo(Screens.CurrentUserScreen(usersListPresenter.users[view.pos]))
        }
    }

    fun loadData() {
        disposables.add(usersRepoRetrofit.getUsers()
            .retry(3)
            .observeOn(uiScheduler)
            .subscribe(
                {
                    usersListPresenter.users.clear()
                    usersListPresenter.users.addAll(it)
                    viewState.updateUsersList()
                },
                { println("onError: ${it.message}") }))
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}