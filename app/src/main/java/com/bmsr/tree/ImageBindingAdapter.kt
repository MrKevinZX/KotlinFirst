package com.bmsr.tree

import android.widget.ImageView
import android.widget.ListAdapter
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object ImageBindingAdapter{
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImagUrl(view: ImageView, imageUrl:String) {
        Glide.with(view.context).load(imageUrl).apply(RequestOptions().circleCrop()).into(view)
    }
}