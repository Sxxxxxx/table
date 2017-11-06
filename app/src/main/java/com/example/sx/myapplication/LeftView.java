package com.example.sx.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

/**
 * Created by sx on 2017/11/6.
 */

public class LeftView extends BaseView {
    public LeftView(Context context) {
        super(context);
        init();
    }

    public LeftView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LeftView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int unitWidth;
    private int unitHeight;
    private Paint linePaint;
    private Paint textPaint;
    private int line;

    private void init() {
        unitHeight = (int) (mDm.density * 50 + 0.5);
        unitWidth = (int) (mDm.density * 50 + 0.5);
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(ContextCompat.getColor(getContext(), R.color.color_e0));
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize((float) (mDm.density * 11 + 0.5));
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.text));
        setBackgroundResource(R.color.color_e6);
    }

    public void setData(int line) {
        this.line = line;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(unitWidth, unitHeight * line);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 1; i <= line; i++) {
            canvas.drawLine(0, unitHeight * i - 1, unitWidth, unitHeight * i, linePaint);
            canvas.drawText("房间" + i, unitWidth / 2, unitHeight * i - unitHeight / 2, textPaint);
        }
        canvas.drawLine(unitWidth - 1, 0, unitWidth, unitHeight * 14, linePaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
