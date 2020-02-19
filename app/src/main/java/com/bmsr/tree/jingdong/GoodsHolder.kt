package com.bmsr.tree.jingdong

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bmsr.tree.databinding.ItemGoodsBinding

class GoodsHolder(rootView : View) : RecyclerView.ViewHolder(rootView) {
    lateinit var binding: ItemGoodsBinding
    init {
        binding = DataBindingUtil.bind<ItemGoodsBinding>(rootView)!!
    }
    fun bindData(name: String) {
        this.binding.goodsName.setText(name)
    }

}