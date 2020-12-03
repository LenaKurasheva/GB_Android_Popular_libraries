package com.lenakurasheva.gb_popular_libraries.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface CurrentUserView : MvpView {
    fun setLoginToToolbar(userLogin: String?)
    fun removeLoginFromToolbar()
    fun init()
    fun updateUsersList()
}