package com.example.mrye.materialdesignui.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mrye.materialdesignui.ModelBean;
import com.example.mrye.materialdesignui.R;
import com.example.mrye.materialdesignui.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Ye on 2016/7/16.
 */
public class mFragment extends Fragment {//将RecyclerView封装在Fragment中
//    private String content;
    private View view;
    private RecyclerView recyclerView;

    private List<ModelBean> beanList;
    private RecyclerAdapter adapter;

    //数据
    private String des[]={"第一张图片","第二张图片","第三张图片","第四张图片",
            "第五张图片","第六张图片","第七张图片"};
    private int resID[]={R.drawable.argame04,R.drawable.argame05,R.drawable.nameid,R.drawable.lufei,
            R.drawable.argame05,R.drawable.nameid,R.drawable.lufei};


    @Override//创建该Fragment的视图
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.activiry_recylerview,container,false);//显示该布局
       return view;
    }

    @Override//当Fragment所在的Activity被启动完成后调用该方法
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initDate();
    }

    private void initDate(){
        beanList =new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            ModelBean bean=new ModelBean();
            bean.setTitle(des[i]);
            bean.setResId(resID[i]);
            beanList.add(bean);

        }
        //创建RecyclerView的适配器
        adapter=new RecyclerAdapter(getActivity(),beanList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object object) {
                Toast.makeText(getActivity(), "点击" + position + "个卡片", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
