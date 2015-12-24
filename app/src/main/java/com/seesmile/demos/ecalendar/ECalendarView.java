package com.seesmile.demos.ecalendar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 说明：日历整体
 * note：
 * Created by FuPei
 * on 2015/12/23 at 19:09
 */
public class ECalendarView extends LinearLayout{

    private String tag = "efupei";
    /** 默认颜色 */
    private final int DEFAULT_COLOR = Color.BLACK;
    /** 横线颜色 */
    private final int DEFAULT_COLOR_LINE = Color.parseColor("#56CE7A");
    /** 默认月份字号 */
    private final int DEFAULT_SIZE = 20;
    /** 星期数显示 */
    private final String[] WEEKS = {"日","一","二","三","四","五","六"};
    /** 数据列表 */
    private ArrayList<ECalendarItem> data;
    /** 时间显示组件 */
    private GridView gv_CalendarView;
    /** 星期显示组件 */
    private GridView gv_WeekView;
    /** 日期适配器 */
    private ECalendarAdapter mAdapter;
    /** 上下文对象 */
    private Context mContext;
    /** 当前月份 */
    private int currentMonth;
    /** 当前日期 */
    private Calendar mCalendar;
    /** 月份显示 */
    private TextView tv_month;
    /** 上个月在日历显示的天数 */
    private int lastMonthDay;
    /** 下个月在日历显示的天数 */
    private int nextMonthDay;
    /** 今天是否签到 */
    private boolean isSign;
    /** 横线 */
    private View view_line;

    public ECalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    public ECalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public ECalendarView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        isSign = false;
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        initMonthView();
        initLineView();
        addView(view_line);
        initWeekView();
        initLineView();
        addView(view_line);
        setCalendarData(new Date());
    }

    /**
     * 是否签到
     * @return
     */
    public boolean isSign() {
        return isSign;
    }

    /**
     * 初始化月份显示控件
     */
    private void initMonthView() {
        tv_month = new TextView(mContext);
        tv_month.setTextColor(DEFAULT_COLOR);
        tv_month.setTextSize(TypedValue.COMPLEX_UNIT_SP, DEFAULT_SIZE);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 8);
        tv_month.setLayoutParams(params);
        tv_month.setGravity(Gravity.CENTER_HORIZONTAL);
        addView(tv_month);
    }

    /**
     * 初始化月份数据
     */
    private void initMonthViewData() {
        int year = mCalendar.get(Calendar.YEAR);
        tv_month.setText(year + "年" + currentMonth + "月");
    }

    /**
     * 设置当前日期
     * @param date 当前日期
     */
    public void setCalendarData(Date date) {
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);
        currentMonth = mCalendar.get(Calendar.MONTH) + 1;
        initMonthViewData();
        initCalendarData();
    }

    /**
     * 获取当前月份的天数
     * @param calendar 当前日期
     * @return 天数
     */
    private int getSumDayInMonth(Calendar calendar) {
        return calendar.getActualMaximum(Calendar.DATE);
    }

    /**
     * 填充日期数据
     */
    private void initCalendarData() {
        gv_CalendarView = new GridView(mContext);
        gv_CalendarView.setNumColumns(7);
        gv_CalendarView.setColumnWidth(20);
        gv_CalendarView.setEnabled(false);
        gv_CalendarView.setGravity(Gravity.CENTER_HORIZONTAL);
        data = new ArrayList<>();
        lastMonthDay = getFirstWeekInMonth();
        ECalendarItem item;
        //添加上月日期空数据
        for(int i = 0; i < lastMonthDay; i++) {
            item = new ECalendarItem(null);
            data.add(item);
        }
        for(int i = 0; i < getSumDayInMonth(mCalendar); i++) {
            item = new ECalendarItem((i + 1) + "");
            data.add(item);
        }
        //下个月的日期空数据
        nextMonthDay = 7 - data.size()%7;
        for(int i = 0; i < nextMonthDay; i++) {
            item = new ECalendarItem(null);
            data.add(item);
        }
        mAdapter = new ECalendarAdapter(mContext, data);
        gv_CalendarView.setAdapter(mAdapter);
        addView(gv_CalendarView, new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT,
                GridView.LayoutParams.WRAP_CONTENT));
    }

    /**
     * 获取当月第一天的星期
     * @return 星期
     */
    private int getFirstWeekInMonth() {
        Calendar calendar = Calendar.getInstance();//获取当前日期
        calendar.add(Calendar.MONTH, mCalendar.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        int firstDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return firstDay;
    }

    /**
     * 初始化week数据
     */
    private void initWeekView() {
        gv_WeekView = new GridView(mContext);
        gv_WeekView.setGravity(Gravity.CENTER_HORIZONTAL);
        gv_WeekView.setEnabled(false);
        gv_WeekView.setNumColumns(7);
        ArrayList<ECalendarItem> items = new ArrayList<>();
        ECalendarItem item;
        for(String value : WEEKS) {
            item = new ECalendarItem(value);
            items.add(item);
        }
        gv_WeekView.setAdapter(new ECalendarAdapter(mContext, items));
        addView(gv_WeekView, new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT,
                GridView.LayoutParams.WRAP_CONTENT));
    }

    /**
     * 签到
     * 如果设置的日期不是当月的日期，则不成功
     * @param date 对指定日期
     */
    public void signAtday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        if(month != (currentMonth - 1)) {
            setCalendarData(date);
        }
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Log.i(tag, "day = " + day);
        signAtDay(day);
        this.isSign = true;
    }

    /**
     * 根据位置进行签到
     * @param index 日期在月份的第几天
     */
    public void signAtDay(int index) {
        data.get(lastMonthDay + index - 1).setIsSignIn(true);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化Line
     */
    private void initLineView() {
        view_line = new TextView(mContext);
        view_line.setBackgroundColor(DEFAULT_COLOR_LINE);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                2);
        params.setMargins(8,0,8,0);
        view_line.setLayoutParams(params);
    }
}






