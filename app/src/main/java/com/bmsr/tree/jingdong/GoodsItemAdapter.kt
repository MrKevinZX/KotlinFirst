package com.bmsr.tree.jingdong

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bmsr.tree.R
import com.bmsr.tree.databinding.ItemGoodsBinding

class GoodsItemAdapter(val mDatas: List<String>) : RecyclerView.Adapter<GoodsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsHolder {
        val view = DataBindingUtil.inflate<ItemGoodsBinding>(LayoutInflater.from(parent.context), R.layout.item_goods, parent, false)
        return GoodsHolder(view.root)
    }

    override fun getItemCount() = mDatas.size

    override fun onBindViewHolder(holder: GoodsHolder, position: Int) {
        holder.bindData(mDatas[position])
    }

}
