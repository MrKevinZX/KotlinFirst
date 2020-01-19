package com.bmsr.tree

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

data class HeadInfo(val imageUrl : String = "", val userName : String, val state : String): BaseObservable()