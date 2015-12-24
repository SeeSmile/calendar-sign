package com.seesmile.demos.ecalendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * 说明：日历单个控件
 * Created by FuPei
 * on 2015/12/23 at 22:40
 */
public class ECanlendarTextView extends TextView {

    private int color;
    private boolean isSignIn;
    private Paint mPaint;
    private final int DEFAULT_COLOR = Color.parseColor("#87D59E");
    private final int DEFAULT_RADIUS = 5;

    public ECanlendarTextView(Context context) {
        super(context);
        initData();
    }

    public ECanlendarTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    public ECanlendarTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData() {
        color = getCurrentTextColor();
        isSignIn = false;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        setGravity(Gravity.CENTER);

    }

    /**
     * 设置背景圆圈
     * @param color 指定的颜色
     */
    public void setSignIn(int color) {
        this.color = color;
        isSignIn = true;
        invalidate();
    }

    /**
     * 设置背景圆圈（默然颜色）
     */
    public void setSignIn() {
        color = 0;
        isSignIn = true;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //如果当前日期签到，则绘制背景圆形
        if (isSignIn) {
            int radius = Math.min(getWidth(), getHeight()) - 6;
            if (color == 0) {
                mPaint.setColor(DEFAULT_COLOR);
            } else {
                mPaint.setColor(color);
            }
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius / 2 - DEFAULT_RADIUS, mPaint);
            setTextColor(Color.WHITE);
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //宽高取一致
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int childWidthSize = getMeasuredWidth();
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
