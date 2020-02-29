package com.bmsr.tree.jingdong.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat

abstract class BaseBehavior : CoordinatorLayout.Behavior<View> {
    var isPull = false
    constructor()

    init {

    }

    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet)
    val TAG = "wdd"

    var defaultPosition = 0
    // 滑动最终位置
    var endPosition = 0
    // 初始位置



    fun updatePosition(defaultPositon : Int, endPosition:Int) {
        this.defaultPosition = defaultPositon
        this.endPosition = endPosition
        Log.i(TAG, "updatePosition endposition= " + endPosition)
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean { //判断监听的方向
        return axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
    }

}