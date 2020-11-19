package com.lenakurasheva.gb_popular_libraries.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lenakurasheva.gb_popular_libraries.R
import com.lenakurasheva.gb_popular_libraries.mvp.model.Model
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.Presenter
import com.lenakurasheva.gb_popular_libraries.mvp.view.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    val presenter = Presenter(Model(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listener = View.OnClickListener {
            when(it.id){
                R.id.btn_counter1 -> presenter.buttonOneClick()
                R.id.btn_counter2 -> presenter.buttonTwoClick()
                R.id.btn_counter3 -> presenter.buttonThreeClick()
            }
        }

        btn_counter1.setOnClickListener(listener)
        btn_counter2.setOnClickListener(listener)
        btn_counter3.setOnClickListener(listener)
    }

    override fun setButtonOneText(text: String) {
        btn_counter1.text = text
    }

    override fun setButtonTwoText(text: String) {
        btn_counter2.text = text
    }

    override fun setButtonThreeText(text: String) {
        btn_counter3.text = text
    }
    
}