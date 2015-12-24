package com.seesmile.demos.ecalendar;

/**
 * 说明：单个日期
 * Created by FuPei
 * on 2015/12/23 at 19:11
 */
public class ECalendarItem {
    /** 显示日期的值 */
    private String value;
    /** 是否签到 */
    private boolean isSignIn;

    public ECalendarItem(String value) {
        this.value = value;
        this.isSignIn = false;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSignIn() {
        return isSignIn;
    }

    public void setIsSignIn(boolean isSignIn) {
        this.isSignIn = isSignIn;
    }
}
