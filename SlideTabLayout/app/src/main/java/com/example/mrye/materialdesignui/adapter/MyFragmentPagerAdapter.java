package com.example.mrye.materialdesignui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Mr.Ye on 2016/8/4.
 */                               //FragmentStatePagerAdapter也是继承自PagerAdapter
public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {//ViewPager的适配器
    private List<Fragment> mFragments;
    private List<String> mTitles;

   //为了管理Activity中的fragments，需要使用FragmentManager
    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments,List<String> titles) {
        super(fm);
        this.mFragments=fragments;
        this.mTitles=titles;
    }
    //继承FragmentPagerAdater要重写getItem()和getCount()两个方法
    @Override
    public Fragment getItem(int position) {//该方法获取第position位置的Fragment
         return mFragments.get(position);
    }

    @Override
    public int getCount() {//该方法返回该Adapter共有多少个Fragment
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {//该方法的返回值决定每个Fragment的标题
        return mTitles.get(position);
    }

}
