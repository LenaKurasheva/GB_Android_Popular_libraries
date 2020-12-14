package com.lenakurasheva.gb_popular_libraries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.lenakurasheva.gb_popular_libraries.R
import com.lenakurasheva.gb_popular_libraries.mvp.model.image.IImageLoader
import com.lenakurasheva.gb_popular_libraries.mvp.presenter.list.IUsersListPresenter
import com.lenakurasheva.gb_popular_libraries.mvp.view.list.UserItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*
import kotlinx.android.synthetic.main.item_user.view.*
import javax.inject.Inject

class UsersRvAdapter(val presenter: IUsersListPresenter) : RecyclerView.Adapter<UsersRvAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)).apply {
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

   inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), UserItemView, LayoutContainer {
        override var pos = -1

        override fun setLogin(text: String) = with(containerView){
            tv_login.text = text
        }

        override fun loadImage(url: String) {
            imageLoader.loadInto(url, iv_image)
        }
    }

}