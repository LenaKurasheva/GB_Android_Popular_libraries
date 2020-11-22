package com.lenakurasheva.gb_popular_libraries.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface CurrentUserView : MvpView {
    fun showData()
}