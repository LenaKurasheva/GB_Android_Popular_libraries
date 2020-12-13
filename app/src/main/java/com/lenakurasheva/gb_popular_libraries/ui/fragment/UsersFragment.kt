package com.lenakurasheva.gb_popular_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.lenakurasheva.gb_popular_libraries.R
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.db.Database
import com.lenakurasheva.gb_popular_libraries.mvp.model.image.IImageLoader
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.UsersPresenter
import com.lenakurasheva.gb_popular_libraries.mvp.view.UsersView
import com.lenakurasheva.gb_popular_libraries.ui.App
import com.lenakurasheva.gb_popular_libraries.ui.BackButtonListener
import com.lenakurasheva.gb_popular_libraries.ui.adapter.UsersRvAdapter
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    @Inject lateinit var database: Database //TODO del
    @Inject lateinit var imageLoader: IImageLoader<ImageView> //TODO доделать, во фрагментах должны остаться только зависимости адаптера(only imageLoader)



    val presenter by moxyPresenter {
        App.instance.appComponent.inject(this) //TODO del

        UsersPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    val adapter by lazy {
        UsersRvAdapter(presenter.usersListPresenter,
           imageLoader)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
        rv_users.layoutManager = LinearLayoutManager(requireContext())
        rv_users.adapter = adapter
    }

    override fun updateUsersList() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}