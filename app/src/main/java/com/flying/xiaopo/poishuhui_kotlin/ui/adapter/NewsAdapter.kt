package com.flying.xiaopo.poishuhui_kotlin.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flying.xiaopo.poishuhui_kotlin.R
import com.flying.xiaopo.poishuhui_kotlin.domain.model.News
import kotlinx.android.synthetic.main.item_child_news.view.*
import java.util.*

class NewsAdapter(var data: List<News> = ArrayList()) : RecyclerView.Adapter<NewsAdapter.NewsAdapterViewHolder>() {
    override fun onBindViewHolder(holder: NewsAdapterViewHolder, position: Int) {
        bindView(holder.itemView, position)
    }

    private fun bindView(itemView: View, position: Int) {
        itemView.tv_title.text = data[position].title
        itemView.tv_time.text = data[position].createdTime
        if(position%2==0) itemView.container.setBackgroundResource(R.color.alpha_grey)
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): NewsAdapterViewHolder? {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_child_news, parent, false)
        return NewsAdapterViewHolder(itemView)
    }

    class NewsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}
