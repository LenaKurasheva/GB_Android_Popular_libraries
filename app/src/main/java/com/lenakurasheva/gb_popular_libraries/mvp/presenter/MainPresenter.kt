package com.lenakurasheva.gb_popular_libraries.mvp.presenter

import com.lenakurasheva.gb_popular_libraries.mvp.view.MainView
import com.lenakurasheva.gb_popular_libraries.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter(): MvpPresenter<MainView>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClick() {
        router.exit()
    }

}