package com.bmsr.tree.jingdong.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.bmsr.tree.R
import kotlinx.android.synthetic.main.activity_jingdong.view.*

class PaneBehavior :BaseBehavior {
    constructor() : super()
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    var totalDy = 0;
    var changeALl = 0;
    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        if (changeALl == 0) {
            changeALl = defaultPosition - endPosition
        }
        if (totalDy < changeALl) {
            totalDy +=dy
            val tem = (totalDy * 100 / changeALl).toFloat() /100
            if (dy <= 0) {
                child.alpha = tem
            } else{
                child.alpha = 1 - tem
            }
            Log.i(TAG, "child alph = " + tem +"total = " +  totalDy + " chall = " + changeALl)

        }

    }

}