package com.example.sx.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sx on 2017/11/3.
 */

public class TopView extends BaseView {
    public TopView(Context context) {
        super(context);
        init();
    }


    public TopView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int unitWidth;
    private int unitHeight;
    private Paint linePaint;
    private Paint textPaint;

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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(unitWidth * 14, unitHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 1; i <= 14; i++) {
            canvas.drawLine(i * unitWidth - 1, 0, i * unitWidth, unitHeight, linePaint);
            canvas.drawText("星期" + i, unitWidth * i - unitWidth / 2, unitHeight / 3, textPaint);
        }
        canvas.drawLine(0, unitHeight - 1, 14 * unitWidth, unitHeight, linePaint);

    }

}
