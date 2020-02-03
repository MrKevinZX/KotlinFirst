package com.bmsr.tree.animation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bmsr.tree.R
import com.bmsr.tree.databinding.ActivityAnimationBinding

/**
 * @Autohr ： yby
 * @CreateDate : 2020-02-03 16:20
 * @Description :
 */
class AnimationActivity : AppCompatActivity(), onItemClickListener {
    override fun onItemClick(position: Int) {
        if (position == 1) {
            explandAnimation()
        } else {
            collapseAnimation()
        }
    }

    private fun collapseAnimation() {
        val moveDiff = -payContiner.y+periodConatiner.y
        ViewCompat.animate(periodConatiner).translationY(moveDiff).setDuration(500).start()
        ViewCompat.animate(payContiner).translationY(moveDiff).setDuration(500).start()

    }

    private fun explandAnimation() {
        val moveDiff = -periodConatiner.y + payContiner.y
        ViewCompat.animate(periodConatiner).translationY(moveDiff).setDuration(500).start()
        ViewCompat.animate(payContiner).translationY(moveDiff).setDuration(500).start()
    }

    val payList = listOf("支付宝收起分期数","杭银展开分期数", "颜值卡","朋友圈","密码支付","小象支付")
    lateinit var periodConatiner:View
    lateinit var payContiner:View
    lateinit var realContainer:View
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityAnimationBinding>(this, R.layout.activity_animation)
        recyclerView=binding.paymethod
        periodConatiner=binding.periodContainer
        payContiner = binding.paymethodContainer
        realContainer = binding.realMoney

        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=PayAdapter(payList,this)
    }
}