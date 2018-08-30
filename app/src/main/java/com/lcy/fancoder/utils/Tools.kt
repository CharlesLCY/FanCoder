package com.lcy.fancoder.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * @author FanCoder.LCY
 * @date   2018/8/30 17:43
 * @email  15708478830@163.com
 * @desc 扩展方法集
 **/
fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun <T : ViewModel> Context.getViewModel(activity: AppCompatActivity, modelClass: Class<T>): T {
    return ViewModelProviders.of(activity).get(modelClass)
}