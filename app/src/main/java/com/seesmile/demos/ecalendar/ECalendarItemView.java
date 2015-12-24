package com.seesmile.demos.ecalendar;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 说明：单个日期显示的View
 * Created by FuPei
 * on 2015/12/23 at 19:18
 */
public class ECalendarItemView extends TextView {
    public ECalendarItemView(Context context) {
        super(context);
    }

    public ECalendarItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ECalendarItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
