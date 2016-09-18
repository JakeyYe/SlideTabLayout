package com.example.mrye.materialdesignui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.mrye.materialdesignui.R;

/**
 * Created by Mr.Ye on 2016/8/17.
 */
public class SwipRefreshLayoutActivity extends AppCompatActivity implements//设置刷新组件接口
        SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout swipe;
    TextView textView;
    private boolean isRefresh=false;//判断是否正在刷新
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiperefresh);

        textView=(TextView)findViewById(R.id.textView);
        swipe=(SwipeRefreshLayout)findViewById(R.id.swipe_container);
        //设置刷新时动画的颜色,可以设置四个
        swipe.setColorSchemeResources(android.R.color.holo_blue_light,android.R.color.holo_red_light,
                android.R.color.holo_orange_light,android.R.color.holo_green_light);
        //设置下拉刷新事件监听器
        swipe.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        if(!isRefresh) {
            isRefresh=true;
            textView.setText("正在刷新");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView.setText("刷新完成");
                    swipe.setRefreshing(false);
                    isRefresh=false;
                }
            },6000);

        }
    }
}
