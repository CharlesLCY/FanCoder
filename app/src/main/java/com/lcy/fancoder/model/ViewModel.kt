package com.lcy.fancoder.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * @author FanCoder.LCY
 * @date   2018/8/30 18:36
 * @email  15708478830@163.com
 * @desc
 **/
open class HomeViewModel : ViewModel() {
    var countData: MutableLiveData<String>? = null

    var dateData: MutableLiveData<String>? = null

    fun getCount(): LiveData<String> {
        if (null == countData) {
            countData = MutableLiveData()
        }
        return countData as MutableLiveData<String>;
    }

    fun setCount(count: String) {
        if (null == countData) {
            this.countData = MutableLiveData();
        }
        countData!!.value = count
    }

    fun getDate(): LiveData<String> {
        if (null == dateData) {
            dateData = MutableLiveData()
        }
        return dateData as MutableLiveData<String>;
    }

    fun setDate(date: String) {
        if (null == dateData) {
            this.dateData = MutableLiveData();
        }
        dateData!!.value = date
    }

    public override fun onCleared() {
        super.onCleared()
        countData = null
        dateData = null
    }

}