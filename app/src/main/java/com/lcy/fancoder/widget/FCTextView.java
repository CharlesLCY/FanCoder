package com.lcy.fancoder.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * @author FanCoder.LCY
 * @date 2018/8/31 13:38
 * @email 15708478830@163.com
 * @desc
 **/
public class FCTextView extends AppCompatTextView {
    public FCTextView(Context context) {
        super(context);
        initView(context);
    }

    public FCTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FCTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/PFZG.otf"));
    }
}
