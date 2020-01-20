package com.bmsr.tree

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProviders
import com.bmsr.tree.databinding.ActivityLifeBinding

class LikeActivity : AppCompatActivity() {
    lateinit var viewmode : SimpleDataModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life)
        val bing : ActivityLifeBinding = DataBindingUtil.setContentView(this, R.layout.activity_life)
        viewmode = ViewModelProviders.of(this, SavedStateViewModelFactory(application, this)).get(SimpleDataModel::class.java)
        bing.lifecycleOwner= this
        bing.data =viewmode
    }

    override fun onPause() {
        super.onPause()
        viewmode.save()
    }
}