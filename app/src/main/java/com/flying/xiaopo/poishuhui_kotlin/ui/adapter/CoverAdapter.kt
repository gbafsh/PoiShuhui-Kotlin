package com.flying.xiaopo.poishuhui_kotlin.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flying.xiaopo.poishuhui_kotlin.R
import com.flying.xiaopo.poishuhui_kotlin.domain.model.Cover
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cover.*
import java.util.*

/**
 * Created by Flying SnowBean on 16-3-5.
 */
class CoverAdapter(val data: List<Cover> = ArrayList()) : RecyclerView.Adapter<CoverAdapter.CoverViewHolder>() {

    override fun onBindViewHolder(holder: CoverViewHolder, position: Int) {
        bindView(holder,position)
    }

    private fun bindView(holder: CoverViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): CoverViewHolder? {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cover, parent, false)
        return CoverViewHolder(itemView)
    }

    class CoverViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}