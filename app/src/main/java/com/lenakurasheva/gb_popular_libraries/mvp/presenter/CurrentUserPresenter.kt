package com.lenakurasheva.gb_popular_libraries.mvp.presenter

import com.lenakurasheva.gb_popular_libraries.mvp.view.CurrentUserView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class CurrentUserPresenter (val router: Router) : MvpPresenter<CurrentUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showData()
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}