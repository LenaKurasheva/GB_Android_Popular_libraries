package com.lenakurasheva.gb_popular_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lenakurasheva.gb_popular_libraries.R
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.GithubRepository
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.RepositoryPresenter
import com.lenakurasheva.gb_popular_libraries.mvp.view.RepositoryView
import com.lenakurasheva.gb_popular_libraries.ui.App
import com.lenakurasheva.gb_popular_libraries.ui.BackButtonListener
import kotlinx.android.synthetic.main.fragment_repository.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    companion object {
        private const val REPOSITORY_ARG = "repository"

        fun newInstance(repository: GithubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repository)
            }
        }
    }

    val presenter: RepositoryPresenter by moxyPresenter {
        val repository = arguments?.getParcelable<GithubRepository>(REPOSITORY_ARG) as GithubRepository
        RepositoryPresenter(repository).apply { App.instance.repositorySubcomponent?.inject(this) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_repository, null)

    override fun init() {}

    override fun setId(text: String) {
        tv_id.text = text
    }

    override fun setTitle(text: String) {
        tv_title.text = text
    }

    override fun setForksCount(text: String) {
        tv_forksCount.text = text
    }

    override fun backPressed() = presenter.backPressed()
}