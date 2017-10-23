package com.example.sx.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

/**
 * Created by sx on 2017/10/19.
 */

public class CustomerView extends View {

    private DisplayMetrics dm;
    private Scroller mScroller;
    private Rect rect;

    public CustomerView(Context context) {
        super(context);
        init();
    }

    public CustomerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(unitWidth * 100, unitHeight * 100);
        Log.i("onMeasure: ", getMeasuredWidth() + "    " + getMeasuredHeight() + "");
        Log.i("dm: ", dm.widthPixels + "    " + dm.heightPixels);
    }

    int unitWidth;
    int unitHeight;
    VelocityTracker mVelocityTracker;

    private int mMinimumVelocity;
    private int mMaximumVelocity;

    private void init() {
        dm = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.RED);
        painttext.setColor(Color.GRAY);
        painttext.setAntiAlias(true);
        paint.setTextSize((float) (dm.density * 20 + 0.5));
        unitHeight = dm.heightPixels / 8;
        unitWidth = dm.widthPixels / 5;
        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mScroller = new Scroller(getContext());

    }

    private Paint paint = new Paint();
    private Paint painttext = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 100; i++) {
            canvas.drawLine(0, dm.heightPixels / 8 * i, unitWidth * 100, dm.heightPixels / 8 * i, painttext);
        }
        for (int i = 0; i < 100; i++) {
            canvas.drawLine(dm.widthPixels / 5 * i, 0, dm.widthPixels / 5 * i, unitHeight * 100, painttext);
        }
//        canvas.drawText("你好", dm.widthPixels / 10, dm.heightPixels / 16 - 20, paint);
    }

    int downX;
    int downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getEdgeFlags() != 0) {
            // Don't handle edge touches immediately -- they may actually belong
            // to one of our
            // descendants.
            return false;
        }
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int index = event.getActionIndex();
                downX = (int) event.getX();
                downY = (int) event.getY();
                event.getX(index);
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
//                Log.i(index + "down", "x= " + event.getX(index) + "   y=" + event.getY(index));
                break;
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();
                int y = (int) event.getY();
//                Log.i("move", "x= " + x + "   y=" + y);
                int moveX = downX - x;
                downX = x;
                int moveY = downY - y;
                downY = y;
//                Log.i("scrollX", "x: " + getScrollX());
//                Log.i("scrollY", "y: " + getScrollY());
                if (moveX < 0) {
                    if (getScrollX() + moveX < 0) {
                        moveX = 0;
                    }
                } else {
                    if (getScrollX() + moveX > getMeasuredWidth() - dm.widthPixels) {
                        moveX = 0;
                    }
                }
                if (moveY < 0) {
                    if (getScrollY() + moveY < 0) {
                        moveY = 0;
                    }
                } else {
                    if (getScrollY() + moveY > getMeasuredHeight() - dm.heightPixels) {
                        moveY = 0;
                    }
                }
                scrollBy(moveX, moveY);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                index = event.getActionIndex();
//                Log.i(index + "Pointerdown", "x= " + event.getX(index) + "   y=" + event.getY(index));
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                float xVelocity = mVelocityTracker.getXVelocity();
                float yVelocity = mVelocityTracker.getYVelocity();
//                mScroller.startScroll(getScrollX(), getScrollY(), 20, 20);
                if (rect == null) {
                    rect = new Rect();
                    ((Activity) getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                    Log.i("init:", rect.top + "");
                }
                if (Math.abs(xVelocity) > xVelocity || Math.abs(yVelocity) > mMinimumVelocity) {
                    mScroller.fling(getScrollX(), getScrollY(), -(int) xVelocity, -(int) yVelocity, 0, getMeasuredWidth() - dm.widthPixels, 0, getMeasuredHeight() - dm.heightPixels + rect.top);
                }
                invalidate();
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
