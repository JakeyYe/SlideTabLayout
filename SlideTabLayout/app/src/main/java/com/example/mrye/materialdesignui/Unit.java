package com.example.mrye.materialdesignui;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Mr.Ye on 2016/8/19.
 */
public class Unit {
    private static Snackbar snackbar;
    public static void showSnacker(View view,String content){
        if (snackbar == null) {
            snackbar = Snackbar.make(view, content, Snackbar.LENGTH_SHORT);
            snackbar.setAction("dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    snackbar.dismiss();//退出
                }
            }).setActionTextColor(Color.BLUE);
        }
        else{
            snackbar.setText(content);
        }
        snackbar.show();
    }
}
