package com.bmsr.tree

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataModel : ViewModel() {
    public val likeNumber :MutableLiveData<String> by lazy {
        MutableLiveData<String>().also {
            getLikeNumber()
        }
    }

    fun getLikeNumber() {
//        likeNumber.value = "Wangdongdong"
    }
    var num = 10
    fun setLikeNume() {
        num ++
        likeNumber.value = num.toString()
    }
}