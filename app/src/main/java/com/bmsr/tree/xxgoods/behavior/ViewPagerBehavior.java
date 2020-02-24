package com.bmsr.tree.xxgoods.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;


/**
 * Created by yuzhijun on 2017/9/21.
 */

public class ViewPagerBehavior extends AbsSearchGoodsBehavior {


    private static final String TAG = "ViewPagerBehavior";
    private View mZhongView;
    private View mSpecialView;
    public ViewPagerBehavior() {
    }
    public ViewPagerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


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
                //滑动recycleview到顶部
                if (recyclerView != null) {
                    recyclerView.scrollBy(0, dy);
                }
                return dy;
            } else if (childY < maxOffset) {
                //下拉到最大高度
                destDy = Math.min(maxOffset, deta);
                child.setY(destDy);
                return (int) (childY - destDy);
            } else {
                child.setY(maxOffset);
                return 0;
            }
        } else {

            if (childY <= minOffset) {
                return 0;
            } else {
                float deltaY = Math.max(minOffset, childY - dy);
                child.setY(deltaY);
                return (int) (childY - deltaY);
            }

        }
    }
    @Override
    protected int rebaseScroll(CoordinatorLayout parent, View child, int dy) {
        int childY = (int) child.getY();
        int deta = childY - dy;
        if (isPull) {
            Log.i(TAG, "rebaseScroll: maxOffset = " + maxOffset);
             if (childY < maxOffset) {
                //下拉到最大高度
                child.setY(deta);
                return dy;
            } else {
                child.setY(maxOffset);
                return 0;
            }
        } else {
            if (deta <= minOffset) {
                //推动到最最顶部
                return 0;
            } else {
                child.setY(deta);
                return dy;
            }
        }
    }

    @Override
    protected boolean handleNestedPreFling(CoordinatorLayout parent, View child, float velocityY) {
        int childY = (int) child.getY();
        if (isPull) {
            if (isBottomScrollTop()) {
                if (childY != maxOffset) {
                    scrollTo(parent, child, maxOffset,100);
                    return true;
                }
            } else {
                if (childY != mediumOffset) {
                    scrollTo(parent, child, mediumOffset, 100);
                    return true;
                }
            }

        } else {
            if (childY != minOffset) {
                scrollTo(parent, child, minOffset, 100);
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
