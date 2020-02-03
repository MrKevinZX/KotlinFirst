package com.bmsr.tree.foldermanager

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bmsr.tree.R
import com.bmsr.tree.databinding.ItemFileBinding
import com.bmsr.tree.databinding.ItemFolderBinding
import com.bmsr.tree.foldermanager.helper.ItemTouchHelperAdapter
import com.bmsr.tree.foldermanager.helper.ItemTouchHelperViewHolder
import com.bmsr.tree.foldermanager.helper.OnStartDragListener
import kotlinx.android.synthetic.main.item_file.view.*
import kotlinx.android.synthetic.main.item_folder.view.*
import java.util.*

/**
 * @Autohr ： yby
 * @CreateDate : 2020-02-01 15:11
 * @Description :
 */

class FileAdapter(var fileList:MutableList<FileInfo>, val startDragListener: OnStartDragListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
, ItemTouchHelperAdapter {
    override fun onItemDismiss(position: Int) {
        fileList.removeAt(position)
        notifyItemRemoved(position)
        Log.i("wdd","onItemDismiss position = " + position)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(fileList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }
    private val TYPE_FOLDER = 2
    private val TYPE_FILE: Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_FILE) {
            val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_file, parent, false)
            return FileHolder(rootView)
        } else  {
            val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_folder, parent, false)
            return FolderHolder(rootView)
        }

    }

    override fun getItemCount(): Int = fileList.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FileHolder) {
            holder.bindData(fileList[position])
            holder.root1.textView5.setOnTouchListener { v, event ->
                if (MotionEventCompat.getActionMasked(event) === MotionEvent.ACTION_DOWN) {
                    startDragListener.onStartDrag(holder)
                }
                false
            }
        } else if (holder is FolderHolder){
            val fileInfo = fileList[position]
            holder.bindData(fileInfo)

        }
    }

    override fun getItemViewType(position: Int): Int {
        if (fileList[position].isFolder) {
            return TYPE_FOLDER
        } else {
            return TYPE_FILE
        }
    }

}

class FileHolder(val root1: View) : BaseViewHolder(root1) {

    private val biding:ItemFileBinding
    init {
        biding = DataBindingUtil.bind(root1)!!

    }

    fun bindData(fileInfo: FileInfo) {
        biding.file = fileInfo
//        root1.visibility = if (fileInfo.visibility) View.VISIBLE else View.GONE
    }

    override fun onItemSelected() {
        itemView.setBackgroundColor(Color.LTGRAY)
    }

    override fun onItemClear() {
        itemView.setBackgroundColor(0)
    }


}

class FolderHolder(val root1: View) : BaseViewHolder(root1) {


    private val binding : ItemFolderBinding
    init {
        binding = DataBindingUtil.bind(root1)!!
    }
    fun bindData(fileInfo: FileInfo) {
        binding.folder = fileInfo
//        root1.visibility = if (fileInfo.visibility) View.VISIBLE else View.GONE
    }
}

abstract class BaseViewHolder(val root: View) : RecyclerView.ViewHolder(root), ItemTouchHelperViewHolder {
    override fun onItemSelected() {
    }

    override fun onItemClear() {
    }
}