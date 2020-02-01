package com.bmsr.tree.foldermanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bmsr.tree.R
import com.bmsr.tree.databinding.ItemFileBinding
import com.bmsr.tree.databinding.ItemFolderBinding

/**
 * @Autohr ： yby
 * @CreateDate : 2020-02-01 15:11
 * @Description :
 */

class FileAdapter(var fileList:List<FileInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
        } else if (holder is FolderHolder){
            holder.bindData(fileList[position])
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

class FileHolder(val root: View) : RecyclerView.ViewHolder(root) {
    private val biding:ItemFileBinding
    init {
        biding = DataBindingUtil.bind(root)!!
    }

    fun bindData(fileInfo: FileInfo) {
        biding.file = fileInfo
    }
}

class FolderHolder(val root: View) : RecyclerView.ViewHolder(root) {
    private val binding : ItemFolderBinding
    init {
        binding = DataBindingUtil.bind(root)!!
    }
    fun bindData(fileInfo: FileInfo) {
        binding.folder = fileInfo
    }
}