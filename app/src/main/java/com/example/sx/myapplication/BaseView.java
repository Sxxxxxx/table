package com.example.sx.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by sx on 2017/11/6.
 */

public class BaseView extends View {

    public BaseView(Context context) {
        super(context);
        initDisplayMetrics();
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initDisplayMetrics();
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDisplayMetrics();
    }
    protected DisplayMetrics mDm;
    private void initDisplayMetrics(){
        mDm = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(mDm);
    }
}
