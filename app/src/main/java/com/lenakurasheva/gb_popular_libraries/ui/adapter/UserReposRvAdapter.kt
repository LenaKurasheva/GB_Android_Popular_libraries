package com.lenakurasheva.gb_popular_libraries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lenakurasheva.gb_popular_libraries.mvp.view.list.RepoItemView

import androidx.recyclerview.widget.RecyclerView
import com.lenakurasheva.gb_popular_libraries.R
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.list.IUserReposListPresenter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repo.*

class UserReposRvAdapter (val presenter: IUserReposListPresenter): RecyclerView.Adapter<UserReposRvAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)).apply {
                containerView.setOnClickListener {
                    presenter.itemClickListener?.invoke(this)
                }
            }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.pos = position
            holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
            presenter.bindView(holder)
        }

        override fun getItemCount() = presenter.getCount()

        inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            RepoItemView, LayoutContainer {
            override fun setName(text: String) {
                tv_name.text = text
            }

            override fun setDescription(text: String) {
                tv_description.text = text            }

            override var pos = -1

        }

}