package com.bmsr.tree.xxgoods.behavior

import android.content.Context
import android.text.TextUtils
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout

/**
 * view behavior 管理类
 */
class HomeSearchManager(
    private val searchbar: View,
    private val shopView: View,
    private val tabView: View,
    private val fliterView: View,
    private val bannerlistView: View,
    private val viewPager: View
) {
    private var searchbarBehavior: AbsSearchGoodsBehavior? = null
    private var shopViewBehavior: AbsSearchGoodsBehavior? = null
    private var tabViewBehavior: AbsSearchGoodsBehavior? = null
    private var fliterViewBehavior: AbsSearchGoodsBehavior? = null
    private var specialViewBehavior: AbsSearchGoodsBehavior? = null
    private var viewPagerBehavior: AbsSearchGoodsBehavior? = null
    var context: Context
    private var mTitleName: String? = null
    private var OriginViewPagerHeight = 0
    fun setHomeSearchBehaviror(from: Int, titleName: String?) {
        var from = from
        from = ViewConfig.HOME_GODOS_SEARCH
        (searchbar.layoutParams as CoordinatorLayout.LayoutParams).behavior =
            createSearchBarBehavior(from)
        (shopView.layoutParams as CoordinatorLayout.LayoutParams).behavior =
            createTopShopBehavior(from)
        (tabView.layoutParams as CoordinatorLayout.LayoutParams).behavior =
            createTabSwitcherBehavior(from)
        (fliterView.layoutParams as CoordinatorLayout.LayoutParams).behavior =
            createFilterBehavior(from)
        (bannerlistView.layoutParams as CoordinatorLayout.LayoutParams).behavior =
            createBannerListBehavior(from)
        (viewPager.layoutParams as CoordinatorLayout.LayoutParams).behavior =
            creatViewPagerBehavior(from, titleName)
        viewPager.requestLayout()
    }

    fun setHomeSearchBehaviror(from: Int) {
        setHomeSearchBehaviror(from, "")
    }

    fun createBannerListBehavior(finalType: Int): AbsSearchGoodsBehavior {
        val originPosition = searchbar.height +
                topShopViewHeight +
                filterViewHeight +
                tabViewHeight
        val mediumPosition = tabViewHeight +
                filterViewHeight +
                searchbar.height - getbannerListHeight()
        val minPosition = getbannerListHeight() - filterViewHeight
        if (specialViewBehavior == null) {
            specialViewBehavior = BannerListBehavior()
        }
        specialViewBehavior!!.updateChildPosition(
            finalType,
            originPosition,
            mediumPosition,
            minPosition
        )
        return specialViewBehavior as AbsSearchGoodsBehavior
    }

    fun createFilterBehavior(finalType: Int): AbsSearchGoodsBehavior {
        val originPosition = searchbar.height +
                topShopViewHeight +
                tabViewHeight
        val mediumPosition = searchbar.height +
                tabViewHeight
        val minPosition = 0f
        if (fliterViewBehavior == null) {
            fliterViewBehavior = FilterTitleBehavior()
        }
        fliterViewBehavior!!.updateChildPosition(
            finalType,
            originPosition,
            mediumPosition,
            minPosition
        )
        return fliterViewBehavior as AbsSearchGoodsBehavior
    }

    fun createSearchBarBehavior(finalType: Int): AbsSearchGoodsBehavior {
        val originPosition = 0f
        val mediumPosition = 0f
        val minPosition = searchbar.height.toFloat()
        if (searchbarBehavior == null) {
            searchbarBehavior = SearchBarBehavior()
        }
        searchbarBehavior!!.updateChildPosition(
            finalType,
            originPosition,
            mediumPosition,
            minPosition
        )
        return searchbarBehavior as AbsSearchGoodsBehavior
    }

    fun createTabSwitcherBehavior(finalType: Int): AbsSearchGoodsBehavior {
        val originPosition = searchbar.height +
                topShopViewHeight
        val mediumPosition = searchbar.height.toFloat()
        val minPosition = searchbar.height.toFloat()
        if (tabViewBehavior == null) {
            tabViewBehavior = TabSwitcherBehavior()
        }
        tabViewBehavior!!.updateChildPosition(
            finalType,
            originPosition,
            mediumPosition,
            minPosition
        )
        return tabViewBehavior as AbsSearchGoodsBehavior
    }

    fun createTopShopBehavior(finalType: Int): AbsSearchGoodsBehavior {
        val originPosition = searchbar.height.toFloat()
        val mediumPosition = searchbar.height +
                tabViewHeight -
                topShopViewHeight
        val minPosition = topShopViewHeight
        if (shopViewBehavior == null) {
            shopViewBehavior = TopShopBehavior()
        }
        shopViewBehavior!!.updateChildPosition(
            finalType,
            originPosition,
            mediumPosition,
            minPosition
        )
        return shopViewBehavior as AbsSearchGoodsBehavior
    }

    fun creatViewPagerBehavior(finalType: Int, titleName: String?): AbsSearchGoodsBehavior {
        if (!TextUtils.isEmpty(titleName)) {
            mTitleName = titleName
        }
        val originPosition = searchbar.height +
                getbannerListHeight() +
                filterViewHeight +
                tabViewHeight +
                topShopViewHeight
        val mediumPosition = searchbar.height +
                filterViewHeight +
                tabViewHeight
        val minPosition = fliterView.height.toFloat()
        if (viewPagerBehavior == null) {
            viewPagerBehavior = ViewPagerBehavior()
        }
        viewPagerBehavior!!.updateChildPosition(
            finalType,
            originPosition,
            mediumPosition,
            minPosition
        )
        return viewPagerBehavior as AbsSearchGoodsBehavior
    }

    val topShopViewHeight: Float
        get() = if (shopView.visibility == View.VISIBLE) shopView.height.toFloat() else 0f

    fun getbannerListHeight(): Float {
        return if (bannerlistView.visibility == View.VISIBLE) bannerlistView.height.toFloat() else 0f
    }

    val tabViewHeight: Float
        get() = if (tabView.visibility == View.VISIBLE) tabView.height.toFloat() else 0f

    val filterViewHeight: Float
        get() = if (fliterView.visibility == View.VISIBLE) fliterView.height.toFloat() else 0f

    fun updateViewPagerHeight(isFilterShow: Boolean) {
        val params =
            viewPager.layoutParams as CoordinatorLayout.LayoutParams
        val oldHeight = viewPager.height
        if (OriginViewPagerHeight == 0 && oldHeight > 0) {
            OriginViewPagerHeight = oldHeight
        }
        if (OriginViewPagerHeight == 0) {
            return
        }
        val newHeight: Int
        newHeight = if (isFilterShow) {
            (OriginViewPagerHeight - fliterView.height)
        } else {
            OriginViewPagerHeight
        }
        params.height = newHeight
        viewPager.layoutParams = params
    }

    init {
        context = searchbar.context
    }
}