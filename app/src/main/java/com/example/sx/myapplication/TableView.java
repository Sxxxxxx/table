package com.example.sx.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import java.util.Date;
import java.util.List;

/**
 * Created by sx on 2017/10/19.
 */

public class TableView extends BaseView {

    private Scroller mScroller;
    private Rect mRect;//计算statusBar高度
    private int unitWidth;
    private int unitHeight;
    private VelocityTracker mVelocityTracker;

    private int mMinimumVelocity;
    private int mMaximumVelocity;
    private Paint paint = new Paint();
    private Paint painttext = new Paint();
    private View mTopView;
    private View mLeftView;

    public TableView(Context context) {
        super(context);
        init();
    }

    public TableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(unitWidth * 14, unitHeight * mData.length);
    }

    /***
     * 设置数据
     * @param line 行数
     * @param startDate 开始日期
     * @param rooms 正在使用的房间信息
     */
    public void setData(int line, Date startDate, List<Room> rooms) {
        mData = new int[line][14];
        invalidate();
    }

    private void init() {
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.RED);
        painttext.setColor(ContextCompat.getColor(getContext(), R.color.color_e0));
        painttext.setAntiAlias(true);
        paint.setTextSize((float) (mDm.density * 20 + 0.5));
        unitHeight = (int) (mDm.density * 50 + 0.5);//格子的宽高为50dp
        unitWidth = (int) (mDm.density * 50 + 0.5);
        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mScroller = new Scroller(getContext());
        setBackgroundResource(R.color.cff);
    }

    /***
     * 滑动的同时同步左边和上边的view
     * @param topView
     * @param leftView
     */
    public void setBothScrollView(View topView, View leftView) {
        this.mTopView = topView;
        this.mLeftView = leftView;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mTopView != null) {
            mTopView.scrollTo(l, 0);
        }
        if (mLeftView != null) {
            mLeftView.scrollTo(0, t);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    int downX;
    int downY;
    int upX;
    int upY;
    int[][] mData;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getEdgeFlags() != 0) {
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
                break;
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();
                int y = (int) event.getY();
                int moveX = downX - x;
                downX = x;
                int moveY = downY - y;
                downY = y;

                if (moveX < 0) {
                    if (getScrollX() + moveX < 0) {
                        moveX = 0;
                    }
                } else {
                    if (getScrollX() + moveX > getMeasuredWidth() - mDm.widthPixels) {
                        moveX = 0;
                    }
                }
                if (moveY < 0) {
                    if (getScrollY() + moveY < 0) {
                        moveY = 0;
                    }
                } else {
                    if (getScrollY() + moveY > getMeasuredHeight() - mDm.heightPixels) {
                        moveY = 0;
                    }
                }
                scrollBy(moveX, moveY);
                break;
            case MotionEvent.ACTION_UP:
                upX = (int) event.getX();
                upY = (int) event.getY();

                if (upX == downX && upY == downY) {//点击事件，选中格子

                }
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                float xVelocity = mVelocityTracker.getXVelocity();
                float yVelocity = mVelocityTracker.getYVelocity();
                if (mRect == null) {
                    mRect = new Rect();
                    ((Activity) getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(mRect);
                }
                if (Math.abs(xVelocity) > xVelocity || Math.abs(yVelocity) > mMinimumVelocity) {
                    mScroller.fling(getScrollX(), getScrollY(), -(int) xVelocity, -(int) yVelocity, 0, getMeasuredWidth() - mDm.widthPixels, 0, getMeasuredHeight() - mDm.heightPixels + mRect.top);
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

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mData != null) {
            for (int i = 0; i < mData.length; i++) {
                canvas.drawLine(0, unitHeight * i, unitWidth * 14, unitHeight * i, painttext);
            }
            for (int i = 0; i < mData[0].length; i++) {
                canvas.drawLine(unitWidth * i, 0, unitWidth * i, unitHeight * mData.length, painttext);
            }
        }
    }
}
