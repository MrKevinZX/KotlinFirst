package com.bmsr.tree

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bmsr.tree.databinding.ActivityLifeBinding
import kotlinx.android.synthetic.main.activity_life.*

class LikeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life)
        val bing : ActivityLifeBinding = DataBindingUtil.setContentView(this, R.layout.activity_life)
        val viewmode = ViewModelProviders.of(this).get(LiveDataModel::class.java)
        bing.lifecycleOwner= this
        bing.data =viewmode
    }
}