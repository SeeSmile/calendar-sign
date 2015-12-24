package cn.wecoder.signcalendar.library;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.LinearLayout;

/**
 * 说明：
 * note：
 * Created by FuPei
 * on 2015/12/23 at 18:58
 */
public class ECalendarView extends LinearLayout {

    private List<>
    private GridView mGridView;
    private Context mContext;

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
        setOrientation(VERTICAL);
        addView(mGridView);
    }

    private void initData() {

    }
}
