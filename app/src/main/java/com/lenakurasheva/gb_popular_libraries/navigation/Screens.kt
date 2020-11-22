package com.lenakurasheva.gb_popular_libraries.navigation

import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubUser
import com.lenakurasheva.gb_popular_libraries.ui.fragment.CurrentUserFragment
import com.lenakurasheva.gb_popular_libraries.ui.fragment.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen() : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }
    class CurrentUserScreen(user: GithubUser): SupportAppScreen() {
        val user = user
        override fun getFragment() = CurrentUserFragment.newInstance(user = user)
    }
}