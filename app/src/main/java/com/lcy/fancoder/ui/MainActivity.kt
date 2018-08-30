package com.lcy.fancoder.ui

import android.arch.lifecycle.Observer
import android.graphics.Typeface
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Chronometer
import com.lcy.fancoder.R
import com.lcy.fancoder.base.BaseActivity
import com.lcy.fancoder.model.HomeViewModel
import com.lcy.fancoder.utils.getViewModel
import com.lcy.fancoder.utils.toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity(), View.OnClickListener {
    var vm: HomeViewModel ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView();
        setData();

    }

    private fun initView() {
        // 设置字体样式
        val tf: Typeface = Typeface.createFromAsset(assets, "fonts/PFZG.otf");
        count.typeface = tf

        // 获取ViewModel实例
        vm = getViewModel(this, HomeViewModel::class.java)
        // 设置点击监听
        log.setOnClickListener(this)

        timer.start()
        timer.onChronometerTickListener = Chronometer.OnChronometerTickListener {
            if (SystemClock.elapsedRealtime() - it.base > 1 * 1000 * 60) {
                it.stop()
            }
        }
    }

    private fun setData() {
        vm!!.setCount("25")
        vm!!.setDate(SimpleDateFormat("yyyy/MM/dd  HH:mm:ss", Locale.getDefault()).format(Date(SystemClock.elapsedRealtime())))

        vm!!.getCount().observe(this, Observer {
            val number: Int = it!!.toInt()
            count.text = it
            if (number < 50) {
                count.setTextColor(ContextCompat.getColor(this, R.color.color_4A90FA))
            }else {
                count.setTextColor(ContextCompat.getColor(this, R.color.color_EE4A4A))
            }
        })

        vm!!.getDate().observe(this, Observer {
            date.text = it
        })
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.log -> toast("LOG")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        vm?.onCleared()
    }

    fun setTime() {
    }
}
