package com.bmsr.tree.xxgoods.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class FilterTitleBehavior extends AbsSearchGoodsBehavior {

    private static final String TAG = "FilterTitleBehavior";

    public FilterTitleBehavior() {
    }

    public FilterTitleBehavior(Context context, AttributeSet attrs) {
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
            if (childY < mediumOffset) {
                //下拉的距离小于headerview的高度
                destDy = Math.min(mediumOffset, deta);
                child.setY(destDy);
                return (int) (childY - destDy);
            } else if (!isBottomScrollTop()) {
                //下拉的时候发现recycle没有下拉到顶部， 先等待recycleview滑动到顶部
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
            if (childY > -minOffset) {
                destDy = Math.max(-minOffset, deta);
                child.setY(destDy);
                return (int) (childY - destDy);
            } else {
                //上滑到顶部位置，filter固定
                child.setY(-minOffset);
                return -dy;
            }
        }
    }

    @Override
    protected boolean handleNestedPreFling(CoordinatorLayout parent, View child, float velocityY) {
        if (isPull) {
            if (isBottomScrollTop()) {
                if (child.getY() != maxOffset) {
                    scrollTo(parent, child, maxOffset, 100);
                    return true;
                }
            } else {
                if (child.getY() != mediumOffset) {
                    scrollTo(parent, child, mediumOffset,100);
                    return true;
                }
            }

        } else {
            if (child.getY() != -minOffset ) {
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
        int childY = (int) child.getY();
        float destDy;
        int deta = childY - dy;
        if (isPull) {
            if (childY < maxOffset) {
                child.setY(deta);
                return -dy;
            } else {
                child.setY(maxOffset);
                return 0;
            }
        } else {
            if (childY > -minOffset) {
                destDy = Math.max(-minOffset, deta);
                child.setY(destDy);
                return (int) (childY - destDy);
            } else {
                //上滑到顶部位置，filter固定
                child.setY(-minOffset);
                return -dy;
            }
        }
    }

}
