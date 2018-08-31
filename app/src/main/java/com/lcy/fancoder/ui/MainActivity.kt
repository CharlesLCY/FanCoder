package com.lcy.fancoder.ui

import android.arch.lifecycle.Observer
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.lcy.fancoder.R
import com.lcy.fancoder.base.BaseActivity
import com.lcy.fancoder.listener.ScreenListener
import com.lcy.fancoder.model.HomeViewModel
import com.lcy.fancoder.utils.SharePrefUtil
import com.lcy.fancoder.utils.getViewModel
import com.lcy.fancoder.utils.toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*



class MainActivity : BaseActivity(), View.OnClickListener {
    var vm: HomeViewModel ?= null
    private var mScreenListener: ScreenListener ?= null

    var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        setData()

    }

    private fun initView() {
        // 设置字体样式
        val tf1: Typeface = Typeface.createFromAsset(assets, "fonts/digital7.ttf")
        val tf2: Typeface = Typeface.createFromAsset(assets, "fonts/PFZG.otf")
        tvCount.typeface = tf1
        count_bg.typeface = tf1
        date.typeface = tf2
//        timer.typeface = tf2

        // 获取ViewModel实例
        vm = getViewModel(this, HomeViewModel::class.java)
        // 设置点击监听
        log.setOnClickListener(this)

//        timer.start()
//        timer.onChronometerTickListener = Chronometer.OnChronometerTickListener {
//            if (SystemClock.elapsedRealtime() - it.base > 1 * 1000 * 60) {
//                it.stop()
//            }
//        }

        mScreenListener = ScreenListener(this)

        mScreenListener!!.begin(object : ScreenListener.ScreenStateListener {
            override fun onScreenOn() {
            }

            override fun onScreenOff() {
            }

            override fun onUserPresent() {
                count++
                SharePrefUtil.setSP("count", count.toString())
                vm!!.setCount(count.toString())
            }
        })

    }

    private fun setData() {
        count = (SharePrefUtil.getSP("count", String::class.java, "0") as String).toInt()
        vm!!.setCount(count.toString())

        date.post(object: Runnable {
            override fun run() {
                vm!!.setDate(SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).format(Date(System.currentTimeMillis())))

                date.postDelayed(this,1000)
            }
        })

        vm!!.getCount().observe(this, Observer {
            val number: Int = it!!.toInt()
            tvCount.text = it
            count_bg.text = generateCountBg(it)
            if (number < 50) {
                tvCount.setTextColor(ContextCompat.getColor(this, R.color.color_4A90FA))
            }else {
                tvCount.setTextColor(ContextCompat.getColor(this, R.color.color_EE4A4A))
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

    private fun generateCountBg(count:String): String {
        var countBg = ""
        var countInt: Int = count.toInt()

        while (countInt > 0) {
            countInt /= 10
            countBg += "8"
        }
        return countBg
    }
}
