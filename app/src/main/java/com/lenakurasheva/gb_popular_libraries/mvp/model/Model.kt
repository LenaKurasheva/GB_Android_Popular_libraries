package com.lenakurasheva.gb_popular_libraries.mvp.model

class Model {

    val counters = mutableListOf(0, 0, 0)

    fun getCurrent(index: Int) = counters[index]

    fun next(index: Int) : Int {
        counters[index]++
        return getCurrent(index)
    }

}