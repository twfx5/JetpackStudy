package com.wang.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private var progress : MutableLiveData<Int>? = null

    fun getProgress() : MutableLiveData<Int> {
        if (progress == null) {
            progress = MutableLiveData<Int>(0)
        }
        return progress as MutableLiveData<Int>
    }
}