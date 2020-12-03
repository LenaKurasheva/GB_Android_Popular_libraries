package com.lenakurasheva.gb_popular_libraries.mvp.view.list

interface RepoItemView: IItemView {
    fun setName(text: String)
    fun setDescription(text: String)
}