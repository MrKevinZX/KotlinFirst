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

    fun setLikeNume(name : String) {
        likeNumber.value = name
    }
}