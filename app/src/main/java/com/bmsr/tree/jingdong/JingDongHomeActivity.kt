package com.bmsr.tree.jingdong

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bmsr.tree.R
import com.bmsr.tree.databinding.ActivityJingdongBinding
import com.bmsr.tree.jingdong.behavior.GoodsBehavior
import com.bmsr.tree.jingdong.behavior.PaneBehavior
import com.bmsr.tree.jingdong.behavior.ScanBehavior
import com.bmsr.tree.jingdong.behavior.SearchBehavior

class JingDongHomeActivity :AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    var mDatas = listOf("苹果","凤梨", "鸡蛋",
        "白菜", "老婆","电脑", "手机", "电视","笔", "房子",
        "毛毛虫", "跳舞课程", "全集课程",
        "白菜", "老婆","电脑", "手机", "电视","笔", "房子")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityJingdongBinding = DataBindingUtil.setContentView(this, R.layout.activity_jingdong)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GoodsItemAdapter(mDatas)

        initBehavior(binding.pane, binding.recyclerView, binding.search, binding.viewScan)
    }

    private fun initBehavior(
        pane: TextView,
        recyclerView: RecyclerView,
        search: TextView,
        viewScan: TextView
    ) {
        pane.post{
            val params = pane.layoutParams as CoordinatorLayout.LayoutParams
            params.behavior = PaneBehavior().also { it.updatePosition(search.bottom,pane.height + search.height) }
        }
        viewScan.post{
            val params = viewScan.layoutParams as CoordinatorLayout.LayoutParams
            params.behavior = ScanBehavior().also { it.updatePosition(0, 0) }
        }
        search.post{
            val params = search.layoutParams as CoordinatorLayout.LayoutParams
            params.behavior = SearchBehavior().also { it.updatePosition(0, pane.bottom) }
//            searchBehavior.updatePosition(0, pane.bottom)
//            params.behavior = searchBehavior
        }

        recyclerView.post{
            val params = recyclerView.layoutParams as CoordinatorLayout.LayoutParams
            params.behavior = GoodsBehavior().also { it.updatePosition(search.bottom, pane.height + search.height) }
//            Log.i("wdd", "default " + (params.behavior as GoodsBehavior).defaultPosition + "endPosition = "+ (params.behavior as GoodsBehavior).endPosition)
        }
    }


}