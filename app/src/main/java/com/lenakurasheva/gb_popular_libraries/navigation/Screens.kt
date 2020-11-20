package com.lenakurasheva.gb_popular_libraries.navigation

import com.lenakurasheva.gb_popular_libraries.ui.fragment.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen() : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }
}