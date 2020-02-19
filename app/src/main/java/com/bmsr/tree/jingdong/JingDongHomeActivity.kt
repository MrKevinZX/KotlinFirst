package com.bmsr.tree.jingdong

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bmsr.tree.R
import com.bmsr.tree.databinding.ActivityJingdongBinding

class JingDongHomeActivity :AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    var mDatas = listOf("苹果","凤梨", "鸡蛋", "白菜", "老婆")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityJingdongBinding = DataBindingUtil.setContentView(this, R.layout.activity_jingdong)
        recyclerView = binding.goodsContainer
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GoodsItemAdapter(mDatas)
    }


}