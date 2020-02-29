package com.bmsr.tree.jingdong.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat

class GoodsBehavior : BaseBehavior{

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor()

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {

        isPull = dy <= 0
        //dy 是滑动距离
        if (dy <= 0){
            //下拉的时候， 下拉是否滑动的原来位置， 若
        } else {
            if (child.y >=  endPosition) {
                ViewCompat.offsetTopAndBottom(child, -dy)
                //假设用户滑动了100px,child 做了90px 的位移，你需要把 consumed［1］的值改成90，
                //这样coordinatorLayout就能知道只处理剩下的10px的滚动。
                consumed[1] = dy
            }

        }


    }

}