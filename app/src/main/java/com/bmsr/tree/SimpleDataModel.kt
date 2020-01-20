package com.bmsr.tree

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle

class SimpleDataModel(val mapplication: Application) : AndroidViewModel(mapplication) {
    lateinit var handle : SavedStateHandle
    lateinit var key : String
    lateinit var preference : SharedPreferences
    constructor(application: Application, handle: SavedStateHandle) : this(application) {
        this.handle = handle
        key = application.resources.getString(R.string.key_name)
        if (!handle.contains(key)) {
            load()
        }
    }
    fun load() {
        preference = mapplication.getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val value = preference.getInt(key, 0)
        handle.set(key, value)
    }

    fun save() {
        getNumber().getValue()?.let { preference.edit().putInt(key, it).apply() }
    }

    fun getNumber() : LiveData<Int> = handle.getLiveData(key)


    fun add(x : Int) {
        handle.set(key, getNumber().value?.plus(x))
        save()
    }
}