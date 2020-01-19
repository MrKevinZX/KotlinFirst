package com.bmsr.tree

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.HeaderViewListAdapter
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.bmsr.tree.databinding.HeadViewDataBinding

class HeadView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        var rootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_head, this, true)

//        val binding = DataBindingUtil.inflate<HeadViewBinding>(R.layout.layout_head, this, true)
//        var binding = DataBindingUtil.bind<HeadViewDataBinding>(rootView)
        val  inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
        var binding= DataBindingUtil.inflate<HeadViewDataBinding>(inflater as LayoutInflater, R.layout.layout_head, this, false)
        if (binding != null) {
            binding.head = HeadInfo("https://img2.woyaogexing.com/2019/05/16/5b9cf0b6b9f24d0c890c85f2e131cc45!400x400.jpeg", "王东东","长时间在线")
            binding.notifyChange()

        }
    }
}