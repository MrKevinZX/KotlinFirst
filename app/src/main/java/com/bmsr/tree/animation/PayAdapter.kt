package com.bmsr.tree.animation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bmsr.tree.R
import com.bmsr.tree.databinding.ItemPayBinding

class PayAdapter(val payList: List<String>,val listener: onItemClickListener) : RecyclerView.Adapter<PayHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PayHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pay,parent, false)
        return PayHolder(view)
    }

    override fun getItemCount(): Int =payList.size

    override fun onBindViewHolder(holder: PayHolder, position: Int) {
        holder.bindData(payList[position])
        holder.itemView.setOnClickListener{
            listener.onItemClick(position)
        }
    }

}

class PayHolder(view: View) : RecyclerView.ViewHolder(view) {
     var payDespView: TextView? = null

    init {
        val binding = DataBindingUtil.bind<ItemPayBinding>(view)
        if (binding != null) {
            payDespView =binding.payDesc
        }
    }
    fun bindData(type:String) {
        payDespView!!.text = type
    }
}

interface onItemClickListener{
    fun onItemClick(position: Int)
}