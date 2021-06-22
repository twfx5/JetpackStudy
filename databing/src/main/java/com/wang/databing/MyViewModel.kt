package com.wang.databing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private var number: MutableLiveData<Int>? = null

    fun getNumber(): MutableLiveData<Int> {
        if (number == null) {
            number = MutableLiveData<Int>(0)
        }
        return number as MutableLiveData<Int>
    }

    fun addNum(num : Int) {
        number?.value = num + number?.value!!
    }
}