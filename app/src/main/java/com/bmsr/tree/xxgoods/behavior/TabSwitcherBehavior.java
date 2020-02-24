package com.bmsr.tree.xxgoods.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.bmsr.tree.R;


public class TabSwitcherBehavior extends AbsSearchGoodsBehavior  {
    public TabSwitcherBehavior() {
    }

    public TabSwitcherBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public int goodsScroll(CoordinatorLayout parent, View child, int dy) {
        int childY = (int) child.getY();
        int deta = childY - dy;
        float destDy;
        View filterView = parent.findViewById(R.id.filter_container);
        if (isPull) {
            if (filterView.getY() < 0) {
                return -dy;
            } else if (childY < mediumOffset) {
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

            if (childY <= -child.getHeight()) {
                return 0;
            } else {
                destDy = Math.max(-child.getHeight(), childY - dy);
                child.setY(destDy);
                return (int) (childY - destDy);
            }
        }
    }


    @Override
    protected boolean handleNestedPreFling(CoordinatorLayout parent, View child, float velocityY) {
        int childY = (int) child.getY();
        if (isPull) {
            if (isBottomScrollTop()) {
                if (childY != maxOffset) {
                    scrollTo(parent, child, maxOffset, 100);
                    return true;
                }
            } else {
                if (childY != mediumOffset) {
                    scrollTo(parent,child, mediumOffset, 100);
                    return true;
                }
            }

        } else {
            if (childY != - child.getHeight()) {
                scrollTo(parent, child, -child.getHeight(), 100);
                return true;
            }
        }
        return false;
    }

    @Override
    protected int htScroll(CoordinatorLayout parent, View child, int dy) {
        return 0;
    }


    @Override
    protected int rebaseScroll(CoordinatorLayout parent, View child, int dy) {
        int childY = (int) child.getY();
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
            if (deta <= -minOffset) {
                return 0;
            } else {
                child.setY(deta);
                return -dy;
            }
        }
    }
}
