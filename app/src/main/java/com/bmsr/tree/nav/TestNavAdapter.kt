package com.bmsr.tree.nav

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bmsr.tree.R
import com.bmsr.tree.databinding.ItemTestBinding
import com.bumptech.glide.Glide

class TestNavpter(val datas: MutableList<String>) : RecyclerView.Adapter<TestNavpter.NavHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavHolder = NavHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_test, parent, false))

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: NavHolder, position: Int) {
        holder.bindData(datas[position])
    }


    class NavHolder(item: View) : RecyclerView.ViewHolder(item) {
        var bindingItem:ItemTestBinding
        fun bindData(url: String) {
            Glide.with(itemView).load(url).into(bindingItem.icon)
        }

        init {
            bindingItem = DataBindingUtil.getBinding(itemView)!!
        }

    }
}
