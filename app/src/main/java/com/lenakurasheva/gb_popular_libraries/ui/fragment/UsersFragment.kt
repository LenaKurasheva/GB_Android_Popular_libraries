package com.lenakurasheva.gb_popular_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lenakurasheva.gb_popular_libraries.R
import com.lenakurasheva.gb_popular_libraries.mvp.model.repo.GithubUsersRepo
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.UsersPresenter
import com.lenakurasheva.gb_popular_libraries.mvp.view.UsersView
import com.lenakurasheva.gb_popular_libraries.ui.App
import com.lenakurasheva.gb_popular_libraries.ui.BackButtonListener
import com.lenakurasheva.gb_popular_libraries.ui.adapter.UsersRvAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter by moxyPresenter {
        UsersPresenter(App.instance.router, GithubUsersRepo(), AndroidSchedulers.mainThread())
    }

    val adapter by lazy {
        UsersRvAdapter(presenter.usersListPresenter)
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