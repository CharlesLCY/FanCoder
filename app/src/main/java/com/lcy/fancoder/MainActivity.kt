package com.lcy.fancoder

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.lcy.fancoder.base.BaseActivity
import com.lcy.fancoder.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView();
        setData();
    }

    private fun initView() {
        val tf: Typeface = Typeface.createFromAsset(assets, "fonts/PFZG.otf");
        count.typeface = tf

        log.setOnClickListener(this)
    }

    private fun setData() {
        count.text = "1000"
        count.setTextColor(ContextCompat.getColor(this, R.color.color_EE4A4A))
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.log -> toast("LOG")
        }
    }
}
