package com.bmsr.tree.jingdong.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat

class SearchBehavior : BaseBehavior {
    constructor() : super()
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        Log.i(TAG, "endpositon = " + endPosition + "..." + this)
        isPull = dy <= 0
        if (isPull) {

        } else {
            val childY = child.y.toInt()
            val deltaY: Int = Math.max(-child.height.toInt(), childY - dy)
            if (child.y > endPosition) {
                ViewCompat.offsetTopAndBottom(child, -dy)
                consumed[1] =-dy
            }
        }
    }
}