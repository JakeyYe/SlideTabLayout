package com.example.mrye.materialdesignui;

/**
 * Created by Mr.Ye on 2016/8/15.
 */
public class ModelBean {//创建一个保存数据的类
    private String title;
    private int resId;

    public String getTitle(){
        return this.title;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
