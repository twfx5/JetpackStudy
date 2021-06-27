package com.wang.livedata

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

    fun addNumber(num: Int) {
        number?.value = num + number?.value!!
    }

    fun loopAddNumber(num: Int) {
        number?.postValue(num + number?.value!!)
    }
}