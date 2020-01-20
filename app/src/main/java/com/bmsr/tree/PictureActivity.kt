package com.bmsr.tree

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bmsr.tree.databinding.ActivityPictureBinding

class PictureActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding :ActivityPictureBinding = DataBindingUtil.setContentView(this, R.layout.activity_picture)
    }
}