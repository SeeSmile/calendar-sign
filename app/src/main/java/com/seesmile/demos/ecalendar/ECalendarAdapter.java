package com.seesmile.demos.ecalendar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.seesmile.demos.R;
import java.util.ArrayList;

/**
 * 说明：日历适配器
 * Created by FuPei
 * on 2015/12/23 at 19:12
 */
public class ECalendarAdapter extends BaseAdapter {

    private ArrayList<ECalendarItem> data;
    private Context mContext;

    public ECalendarAdapter(Context context, ArrayList<ECalendarItem> data) {
        this.mContext = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holder;
        if (convertView == null) {
            holder = new HolderView();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_calendar, null);
            holder.tv_value = (ECanlendarTextView) convertView.findViewById(R.id.tv_value);
            convertView.setTag(holder);
        } else {
            holder = (HolderView) convertView.getTag();
        }
        final ECalendarItem item = data.get(position);
        holder.tv_value.setText(item.getValue());
        if(item.isSignIn()) {
            holder.tv_value.setSignIn();
        }
        return convertView;
    }

    class HolderView {
        ECanlendarTextView tv_value;
    }
}
