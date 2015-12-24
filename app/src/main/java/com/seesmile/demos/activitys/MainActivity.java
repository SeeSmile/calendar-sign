package com.seesmile.demos.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.seesmile.demos.R;
import com.seesmile.demos.ecalendar.ECalendarView;
import java.util.Date;
import java.util.Random;

public class MainActivity extends Activity {

    private ECalendarView view;
    private Button btn_sign;
    private Button btn_days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        setListener();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        view = (ECalendarView) findViewById(R.id.main_calendar);
        btn_days = (Button) findViewById(R.id.btn_days);
        btn_sign = (Button) findViewById(R.id.btn_sign);
    }

    private void setListener() {
        btn_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                for(int i = 0; i < 8; i++) {
                    view.signAtDay(random.nextInt(31) + 1);
                }
            }
        });

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.signAtday(new Date());
            }
        });
    }

    private void initData() {

    }
}
