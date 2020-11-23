package com.lenakurasheva.gb_popular_libraries.mvp.presenter.list

import com.lenakurasheva.gb_popular_libraries.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}