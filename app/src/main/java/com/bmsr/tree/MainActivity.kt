package com.bmsr.tree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bmsr.tree.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bing : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bing.viewmodel = ViewModelBean("王东东", "12")
    }
}
