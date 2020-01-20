package com.bmsr.tree

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_life.*

class LikeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life)
        val titleView = textView2
        val btn = button3
        val viewmode = ViewModelProviders.of(this).get(LiveDataModel::class.java)
        viewmode.likeNumber.observe(this, Observer<String> { name ->
            titleView.text = name
        })
        btn.setOnClickListener{
            viewmode.setLikeNume("aaaaa")
        }
    }
}