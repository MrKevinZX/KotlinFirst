package com.bmsr.tree.xxgoods.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * 搜索结果页专场banner位动画
 */
public class BannerListBehavior extends AbsSearchGoodsBehavior  {
    private static final String TAG = "wdd-" + BannerListBehavior.class.getSimpleName();

    public BannerListBehavior() {
    }

    public BannerListBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 滑动
     *
     * @param parent
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
            if (childY < mediumOffset) {
                destDy = Math.min(mediumOffset, deta);
                child.setY(destDy);
                return (int) (childY - destDy);
            } else if (!isBottomScrollTop()) {
                child.setY(mediumOffset);
                return 0;
            } else if (childY < maxOffset) {
                destDy = Math.min(maxOffset, deta);
                child.setY(destDy);
                return (int) (childY - destDy);
            } else {
                child.setY(maxOffset);
                return 0;
            }
        } else {

            if (childY <= -minOffset) {
                return 0;
            } else {

                float deltaY = Math.max(-minOffset, childY - dy);
                child.setY(deltaY);
                return (int) (childY - deltaY);

            }

        }
    }


    @Override
    protected int rebaseScroll(CoordinatorLayout parent, View child, int dy) {
        return dy;
    }


    @Override
    protected boolean handleNestedPreFling(CoordinatorLayout parent, View child, float velocityY) {
        if (isPull) {
            if (isBottomScrollTop()) {
                //recycleview滑动到顶部
                if (child.getY() != maxOffset) {
                    scrollTo(parent, child, maxOffset, 100);
                }
            } else {
                if (child.getY() != mediumOffset) {
                    scrollTo(parent,child, mediumOffset, 100);
                    return true;
                }
            }
        } else {
            //上滑操作
            if (child.getY() != -minOffset) {
                scrollTo(parent,child, -minOffset, 100);
                return true;
            }
        }
        return false;
    }

    @Override
    protected int htScroll(CoordinatorLayout parent, View child, int dy) {
        return goodsScroll(parent, child, dy);
    }
}
