package com.bmsr.tree.foldermanager

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bmsr.tree.R
import com.bmsr.tree.databinding.ActivityFileBinding
import com.bmsr.tree.foldermanager.helper.OnStartDragListener
import com.bmsr.tree.foldermanager.helper.SimpleItemTouchHelperCallback
import javax.security.auth.login.LoginException

/**
 * @Autohr ： yby
 * @CreateDate : 2020-02-01 15:04
 * @Description :
 */
class FileActivity : AppCompatActivity(), OnStartDragListener {
    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder?) {
        if (viewHolder != null) {
            itemTouchHelper.startDrag(viewHolder)
        }
    }
    lateinit var itemTouchHelper: ItemTouchHelper
    lateinit var recyclerView: RecyclerView
    lateinit var mDatas :MutableList<FileInfo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("wdd", "onCreate")
        val binding = DataBindingUtil.setContentView<ActivityFileBinding>(this, R.layout.activity_file)
        recyclerView = binding.recyclerView
        initDatas()
        val adapter = FileAdapter(mDatas, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val callback = SimpleItemTouchHelperCallback(adapter)
        itemTouchHelper = ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun initDatas() {
        mDatas = ArrayList()
        var isFirst = true
        for (i in 0..99){
            var fileInfo : FileInfo
            if (i%6 != 0 && isFirst) {
                fileInfo = FileInfo("文件信息-" + i, false, false)
                mDatas.add(fileInfo)
            } else if (i%6 == 0){
                isFirst = false
                fileInfo = FileInfo("文件夹-" + (i / 6), true, true)
                mDatas.add(fileInfo)
            }
            Log.i("wdd", "信息 = "+ i)

        }
    }

}