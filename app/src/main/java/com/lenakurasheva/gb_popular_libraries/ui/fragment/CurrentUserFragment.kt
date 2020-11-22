package com.lenakurasheva.gb_popular_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lenakurasheva.gb_popular_libraries.R
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubUser
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.CurrentUserPresenter
import com.lenakurasheva.gb_popular_libraries.mvp.view.CurrentUserView
import com.lenakurasheva.gb_popular_libraries.ui.App
import com.lenakurasheva.gb_popular_libraries.ui.BackButtonListener
import kotlinx.android.synthetic.main.fragment_current_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class CurrentUserFragment : MvpAppCompatFragment(), CurrentUserView, BackButtonListener {

    companion object {
        fun newInstance(user: GithubUser) = CurrentUserFragment().apply {
            arguments = Bundle().apply {
                putParcelable("user", user)
            }
        }
    }

    val presenter by moxyPresenter {
        CurrentUserPresenter(App.instance.router)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_current_user, null)

    override fun backPressed() = presenter.backClick()

    override fun showData() {
        login.setText(this.arguments?.getParcelable<GithubUser>("user")?.login)
    }

}