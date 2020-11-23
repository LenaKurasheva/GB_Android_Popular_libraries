package com.lenakurasheva.gb_popular_libraries.mvp.presenter

import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubUser
import com.lenakurasheva.gb_popular_libraries.mvp.view.CurrentUserView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class CurrentUserPresenter (val router: Router, val user: GithubUser?) : MvpPresenter<CurrentUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(user?.login)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}