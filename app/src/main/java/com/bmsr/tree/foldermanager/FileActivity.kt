package com.bmsr.tree.foldermanager

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bmsr.tree.R
import com.bmsr.tree.databinding.ActivityFileBinding
import javax.security.auth.login.LoginException

/**
 * @Autohr ： yby
 * @CreateDate : 2020-02-01 15:04
 * @Description :
 */
class FileActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var mDatas :MutableList<FileInfo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("wdd", "onCreate")
        val binding = DataBindingUtil.setContentView<ActivityFileBinding>(this, R.layout.activity_file)
        recyclerView = binding.recyclerView
        initDatas()
        recyclerView.adapter = FileAdapter(mDatas)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initDatas() {
        mDatas = ArrayList()
        for (i in 0..99){
            var fileInfo : FileInfo
            if (i%10 == 0) {
                fileInfo = FileInfo("文件夹-" + (i / 10), true)
            } else {
                fileInfo = FileInfo("文件信息-" + i, false)
            }
            Log.i("wdd", "信息 = "+ i)
            mDatas.add(fileInfo)
        }
    }

}