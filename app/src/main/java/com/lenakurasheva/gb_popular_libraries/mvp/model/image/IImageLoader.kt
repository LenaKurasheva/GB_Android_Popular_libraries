package com.lenakurasheva.gb_popular_libraries.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}