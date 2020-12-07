package com.lenakurasheva.gb_popular_libraries.mvp.view.list

interface UserItemView : IItemView {
    fun setLogin(text: String)
    fun loadImage(url: String)
}