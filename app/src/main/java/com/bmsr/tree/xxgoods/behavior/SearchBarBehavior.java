package com.bmsr.tree.xxgoods.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.bmsr.tree.R;


/**
 * 顶部栏behavior
 *
 */
public class SearchBarBehavior extends AbsSearchGoodsBehavior {


    private static final String TAG = "SearchBarBehavior";

    public SearchBarBehavior() {
    }

    public SearchBarBehavior(Context context, AttributeSet attrs) {
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
        View tabSwitcher = parent.findViewById(R.id.result_tab_switcher);

        if (isPull) {
            if(tabSwitcher.getY() < 0){
                return 0;
            } else if (child.getY() <0){
                child.setY(deta);
                return -dy;
            } else {
                child.setY(0);
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
    protected boolean handleNestedPreFling(CoordinatorLayout parent, View child, float velocityY) {
        if (isPull) {
            if (child.getY() != 0) {
                scrollTo(parent, child, 0, 100);
                return true;
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
        int childY = (int) child.getY();
        int deta = childY - dy;
        if (isPull) {
            if (child.getY() <0){
                child.setY(deta);
                return -dy;
            } else {
                child.setY(0);
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
        return goodsScroll(parent, child, dy);
    }

}
