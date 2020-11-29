package com.lenakurasheva.gb_popular_libraries.mvp.presenter

import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubUser
import com.lenakurasheva.gb_popular_libraries.mvp.model.repo.GithubUsersRepo
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.list.IUsersListPresenter
import com.lenakurasheva.gb_popular_libraries.mvp.view.UsersView
import com.lenakurasheva.gb_popular_libraries.mvp.view.list.UserItemView
import com.lenakurasheva.gb_popular_libraries.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router


class UsersPresenter(val router: Router, val usersRepo: GithubUsersRepo, val scheduler: Scheduler) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUsersListPresenter {
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        val users = mutableListOf<GithubUser>()

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
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
        disposables.add(usersRepo.getUsers()
            .retry(3)
            .observeOn(scheduler)
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