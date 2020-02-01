package com.bmsr.tree.nav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bmsr.tree.R
import com.bmsr.tree.databinding.FragmentNarBinding

class NavFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var datas: MutableList<String>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentNarBinding>(inflater, R.layout.fragment_nar, container, false)
        recyclerView = binding.recyclerView
//        recyclerView.adapter = TestNavAdapter(datas)
        return binding.root
    }
}