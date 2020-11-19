package com.lenakurasheva.gb_popular_libraries.mvp.presenter

import com.lenakurasheva.gb_popular_libraries.mvp.model.Model
import com.lenakurasheva.gb_popular_libraries.mvp.view.MainView

class Presenter(val model: Model, val view: MainView) {

    fun buttonOneClick(){
        val nextValue = model.next(0)
        view.setButtonOneText(nextValue.toString())
    }

    fun buttonTwoClick(){
        val nextValue = model.next(1)
        view.setButtonTwoText(nextValue.toString())
    }

    fun buttonThreeClick(){
        val nextValue = model.next(2)
        view.setButtonThreeText(nextValue.toString())
    }

}