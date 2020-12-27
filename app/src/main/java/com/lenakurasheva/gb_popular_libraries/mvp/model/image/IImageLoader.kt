package com.lenakurasheva.gb_popular_libraries.mvp.model.image

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable

interface IImageLoader<T> {
//    fun loadInto(url: String, container: T): Completable
    fun loadInto(url: String, container: T)
}