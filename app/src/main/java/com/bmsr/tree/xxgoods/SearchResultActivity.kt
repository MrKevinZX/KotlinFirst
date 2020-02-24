package com.bmsr.tree.xxgoods

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bmsr.tree.R
import com.bmsr.tree.databinding.ActivityResultBinding
import com.bmsr.tree.jingdong.GoodsItemAdapter
import com.bmsr.tree.xxgoods.behavior.HomeSearchManager
import com.bmsr.tree.xxgoods.behavior.ViewConfig
import kotlinx.android.synthetic.main.activity_result.*

class SearchResultActivity: AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var homeSearchManager: HomeSearchManager
    var mDatas = listOf("苹果","凤梨", "鸡蛋",
        "白菜", "老婆","电脑", "手机", "电视","笔", "房子",
        "毛毛虫", "跳舞课程", "全集课程",
        "白菜", "老婆","电脑", "手机", "电视","笔", "房子")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityResultBinding>(this, R.layout.activity_result)
        recyclerView = binding.vpSearch
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GoodsItemAdapter(mDatas)
        homeSearchManager = HomeSearchManager(binding.resultSearchHeader,
            binding.resultTopShop,
            binding.resultTabSwitcher,
            binding.filterContainer,
            binding.resultBannerList,
            binding.vpSearch)
        homeSearchManager.setHomeSearchBehaviror(ViewConfig.HOME_GODOS_SEARCH)
    }

}