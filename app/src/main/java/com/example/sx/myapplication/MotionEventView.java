package com.example.sx.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by sx on 2017/10/23.
 */

public class MotionEventView extends View {
    public MotionEventView(Context context) {
        super(context);
    }

    public MotionEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MotionEventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            Log.i("onTouchEvent: ", "" + event.getX() + "   " + event.getY());
        } else if (event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
            Log.i("point: ", "" + event.getX() + "   " + event.getY());
        }
        return true;
    }
}
