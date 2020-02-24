package com.bmsr.tree.xxgoods.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.OverScroller;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bmsr.tree.R;

public abstract class AbsSearchGoodsBehavior extends NViewOffsetBehavior<View> {
    public int mTabModel = ViewConfig.HOME_GODOS_SEARCH;
    private static final String TAG = "wdd";
    //初始的视图的偏移也就是最大的偏移量
    float maxOffset = 0;
    //最小的偏移
    float minOffset = 0;
    //中间值
    float mediumOffset = 0 ;
    boolean isPull = false;
    int mDy;
    boolean isInited;
    public RecyclerView recyclerView;
    boolean isAnmiationRunning;
    private int mLastScrollState;
    private int mPreItemPosition = -1;

    public AbsSearchGoodsBehavior() {
    }

    public AbsSearchGoodsBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onMeasureChild(@NonNull CoordinatorLayout parent, @NonNull View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        child.measure(w,h);
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    CoordinatorLayout parent;
    View child;
    @Override
    public boolean onLayoutChild(@NonNull final CoordinatorLayout parent, @NonNull final View child, int layoutDirection) {
        this.child = child;
        this.parent = parent;
        super.onLayoutChild(parent,child,layoutDirection);
        parent.onLayoutChild(child, layoutDirection);

        recyclerView = parent.findViewById(R.id.vp_search);
        if (recyclerView != null) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    Log.i(TAG, child.getClass().getSimpleName() + "onScrollStateChanged:  state = "  + newState);
                    if (mLastScrollState != newState && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        handleNestedPreFling(parent, child, 0);
                    }
                    mLastScrollState = newState;
                }
            });
        }
        Log.e("AAAAAA","RecyclerView == " + recyclerView);

        if (isInited) {
            return true;
        }

        child.setY(maxOffset);
        return true;
    }

    @Override
    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY) {
        boolean result;
        /*Log.i(TAG, child.getClass().getSimpleName() + "onNestedPreFling" + velocityY + "childY = " + child.getY());
        result = handleNestedPreFling(coordinatorLayout, child, velocityY);*/
        return false;
    }

    protected abstract boolean handleNestedPreFling(CoordinatorLayout parent, View child, float velocityY);

    @Override
    public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY, boolean consumed) {
        fling(coordinatorLayout, child, (int) -minOffset, (int) maxOffset, velocityY);
        return true;
    }

    public boolean fling(CoordinatorLayout parent, View child, int minOffset, int maxOffset, float velocityY){
        if (mFlingRunnable != null) {
            child.removeCallbacks(mFlingRunnable);
            mFlingRunnable = null;
        }

        if (mOverScroller == null) {
            mOverScroller = new OverScroller(parent.getContext());
        }

        mOverScroller.fling(
                0, getTopAndBottomOffset(), // curr
                0, Math.round(velocityY), // velocity.
                0, 0, // x
                minOffset, maxOffset); // y

        if (mOverScroller.computeScrollOffset()) {
            mFlingRunnable = new FlingRunnable(parent, child);
            ViewCompat.postOnAnimation(child, mFlingRunnable);
            return true;
        } else {
            onFlingFinished(parent, child);
            return false;
        }
    }

    private void onFlingFinished(CoordinatorLayout parent, View child) {
        setHeaderTopBottomOffset(parent, child, mOverScroller.getFinalY());
    }


    private void setHeaderTopBottomOffset(CoordinatorLayout parent, View child, int newOffset) {
        final int curOffset = getTopAndBottomOffset();
        int consumed = 0;

        if (minOffset != 0 && curOffset >= minOffset && curOffset <= maxOffset) {
            // If we have some scrolling range, and we're currently within the min and max
            // offsets, calculate a new offset
            newOffset = (int) (newOffset < minOffset ? minOffset : (newOffset > maxOffset ? maxOffset : newOffset));

            if (curOffset != newOffset) {
                setTopAndBottomOffset(newOffset);
                // Update how much dy we have consumed
                consumed = curOffset - newOffset;
            }
        }
    }

    private Runnable mFlingRunnable;
    private OverScroller mOverScroller;
    private class FlingRunnable implements Runnable {
        private final CoordinatorLayout mParent;
        private final View mLayout;

        FlingRunnable(CoordinatorLayout parent, View layout) {
            mParent = parent;
            mLayout = layout;
        }

        @Override
        public void run() {
            if (mLayout != null && mOverScroller != null) {
                if (mOverScroller.computeScrollOffset()) {
                    setHeaderTopBottomOffset(mParent, mLayout, mOverScroller.getCurrY());
                    // Post ourselves so that we run on the next animation
                    ViewCompat.postOnAnimation(mLayout, this);
                } else {
                    onFlingFinished(mParent, mLayout);
                }
            }
        }
    }


    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        //判断监听的方向
        boolean result =  (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
        return result ;
    }
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        isPull = dy <=0;
        mDy = dy;
        isInited = true;
        if (mTabModel == ViewConfig.HOME_GODOS_SEARCH) {
            consumed[1] = goodsScroll(coordinatorLayout,child, dy);
        } else if (mTabModel == ViewConfig.HOME_REBASE_SEARCH) {
            consumed[1] = rebaseScroll(coordinatorLayout,child, dy);
        } else if (mTabModel == ViewConfig.HT_SEARCH) {
            consumed[1] = htScroll(coordinatorLayout, child, dy);
        }

    }

    protected abstract int htScroll(CoordinatorLayout parent, View child, int dy);

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        Log.i(TAG, child.getClass().getSimpleName() + " childy = " + child.getY() + "onNestedScroll:  unDy = " + dyUnconsumed);
    }

    protected abstract int rebaseScroll(CoordinatorLayout parent, View child, int dy);

    public void scrollTo(final CoordinatorLayout parent, final View child, final float y, int duration){
        if (isAnmiationRunning) {
            return;
        }
        isAnmiationRunning = true;
        final Scroller scroller = new Scroller(parent.getContext());
        final float originY =  child.getY();
        scroller.startScroll(0, (int) originY,0, (int) (y - originY),duration);
        ViewCompat.postOnAnimation(child, new Runnable() {
            @Override
            public void run() {
                if (scroller.computeScrollOffset()) {
                    float curScrollY = scroller.getCurrY();

                    if (originY > y) {
                        child.setY(Math.max(curScrollY,y));
                    } else {
                        child.setY(Math.min(curScrollY,y));
                    }

                    if (curScrollY >= y) {
                        isAnmiationRunning = false;
                    }

                    ViewCompat.postOnAnimation(child, this);
                }
            }
        });
    }

    protected abstract int goodsScroll(CoordinatorLayout coordinatorLayout, View child, int dy);

    /**
     * recycle 滑动到顶部
     * @return
     */
    protected boolean isBottomScrollTop() {
        //不可以可以纵向向上滑动， 表示滑动到顶部
        Log.e("AAAAAA","recyclerview == " + recyclerView);
        return recyclerView != null && !recyclerView.canScrollVertically(-1);
    }

    public void updateChildPosition(int finalType, float originPosition, float mediumPosition, float minPosition) {
        this.maxOffset = originPosition;
        this.mediumOffset = mediumPosition;
        this.minOffset = minPosition;
        this.mTabModel = finalType;
        if (child != null && parent != null) {
            scrollTo(parent, child, maxOffset, 100);
        }
    }
}
