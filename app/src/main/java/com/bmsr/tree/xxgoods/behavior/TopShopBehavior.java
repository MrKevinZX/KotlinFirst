package com.bmsr.tree.xxgoods.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * 中间停靠的栏目
 * Created by yuzhijun on 2017/9/21.
 */
public class TopShopBehavior extends AbsSearchGoodsBehavior{
    public TopShopBehavior() {
    }

    public TopShopBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    /**
     * 滑动
     * @param child 滑动view
     * @param dy 滑动距离
     * @return
     */
    @Override
    public int goodsScroll(CoordinatorLayout parent, View child, int dy) {
        int childY = (int) child.getY();

        int deta = childY - dy;
        float destDy;
        if (isPull) {
            if (childY  < mediumOffset)  {
                destDy = Math.min(mediumOffset, deta);
                child.setY(destDy);
                return (int) (childY - destDy);
            } else if (!isBottomScrollTop()) {
                //recycleview 未置顶前不做滑动
                child.setY(mediumOffset);
                return 0;
            }else if (childY < maxOffset) {
                destDy = Math.min(maxOffset, deta);
                child.setY(destDy);
                return (int) (childY - destDy);
            } else{
                child.setY(maxOffset);
                return 0;
            }
        } else {

            if (childY <= -minOffset) {
                return 0;
            } else {
                destDy = Math.max(-minOffset, childY - dy);
                child.setY(destDy);
                return (int) (childY - destDy);
            }

        }
    }

    @Override
    protected boolean handleNestedPreFling(CoordinatorLayout parent, View child, float velocityY) {
        if (isPull) {
            if (isBottomScrollTop()) {
                if (child.getY() != maxOffset) {
                    scrollTo(parent, child, maxOffset, 100);
                }
            } else {
                if (child.getY()   != mediumOffset) {
                    scrollTo(parent, child, mediumOffset, 100);
                    return true;
                }
            }

        } else {
            if (child.getY() != -minOffset) {
                scrollTo(parent, child, -minOffset, 100);
                return true;
            }
        }
        return false;
    }

    @Override
    protected int htScroll(CoordinatorLayout parent, View child, int dy) {
        return goodsScroll(parent, child, dy);
    }


    @Override
    protected int rebaseScroll(CoordinatorLayout parent, View child, int dy) {
        return dy;
    }
}
