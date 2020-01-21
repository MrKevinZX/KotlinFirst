package com.bmsr.tree.nav

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.bmsr.tree.R
import com.bmsr.tree.databinding.ActivityNavBinding

class NavActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityNavBinding = DataBindingUtil.setContentView(this, R.layout.activity_nav)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment).navigateUp()
    }
}