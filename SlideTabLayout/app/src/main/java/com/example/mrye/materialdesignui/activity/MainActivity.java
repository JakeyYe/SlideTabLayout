package com.example.mrye.materialdesignui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mrye.materialdesignui.R;
import com.example.mrye.materialdesignui.Unit;
import com.example.mrye.materialdesignui.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    CoordinatorLayout container;
    NavigationView navigationView;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar控件的一系列设置
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("应用");//设置主标题
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        container = (CoordinatorLayout) findViewById(R.id.action_bar_container);

        //TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        List<String> tabList = new ArrayList<>();
        tabList.add("Tab1");
        tabList.add("Tab2");
        tabList.add("Tab3");
        tabList.add("Tab4");
        tabList.add("Tab5");
        tabList.add("Tab6");
        tabList.add("Tab7");
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));//添加tab选项卡
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText("Tab4"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab5"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab6"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab7"));

        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < tabList.size(); i++) {
            Fragment f = new mFragment();
            fragmentList.add(f);
        }


        //Fragment+ViewPager+FragmentPagerAdapter组合的使用
        //ViewPager是Fragment容器，可以同时管理多个Fragment，并允许多个Fragment切换时提供动画效果
        viewPager = (ViewPager) findViewById(R.id.viewpager);//Tab与ViewPager一起滑动
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),
                fragmentList, tabList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来
//        tabLayout.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器
        //设置TabLayout的选项卡Tab监听
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中tab的逻辑
//                viewPager.setCurrentItem(tab.getPosition());//这一段代码加上也好像没什么作用
                Snackbar.make(container, tab.getPosition() + " OnTabSeleted", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中tab的逻辑
                Snackbar.make(container, tab.getPosition() + " OnTabUnSeleted", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //再次选中tab的逻辑
                Snackbar.make(container, tab.getPosition() + " OnTabReSeleted", Snackbar.LENGTH_SHORT).show();
            }
        });

        //浮动按钮的一系列设置
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar组件（Toast）
                Unit.showSnacker(view, "Here's a Snacker");
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //左上角导航栏动画效果
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //R.string.navigation_drawer_open, R.string.navigation_drawer_close代表drawer的开和关
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //drawer里的菜单栏
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);//恢复drawer里菜单栏里的ltem前图标的原来颜色
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {//实体键返回键事的件监听器
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            doExitApp();
        }
    }

    private long exitTime = 0;

    public void doExitApp() {//System.currentTimeMillis()获得当前时间
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();

        }
    }

    //Toolbar组件右上角弹出的菜单栏
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//将布局文件mian_xml的menu添加到布局上
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {//Android的Menu状态动态设置方法
        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
                menu.findItem(R.id.menu_night_mode_system).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_AUTO:
                menu.findItem(R.id.menu_night_mode_auto).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                menu.findItem(R.id.menu_night_mode_night).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_NO:
                menu.findItem(R.id.menu_night_mode_day).setChecked(true);
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//Toolbar组件右上角弹出的菜单栏事件监听
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent com.example.mrye.materialdesignui.activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "星星", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.menu_night_mode_system) {
            setNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
        if (id == R.id.menu_night_mode_day) {
            setNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        if (id == R.id.menu_night_mode_night) {
            setNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        if (id == R.id.menu_night_mode_auto) {
            setNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNightMode(@AppCompatDelegate.NightMode int nightMode) {
        AppCompatDelegate.setDefaultNightMode(nightMode);

        if (Build.VERSION.SDK_INT >= 11) {//判断Android Api版本是否大于11
            recreate();//重启Activity，recreate()方法重启使设置生效
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) { //drawer侧滑视图导航栏ltem事件监听器
        // Handle navigation view item clicks here.
        int id = item.getItemId();
//        MenuItem menuItem = navigationView.getMenu().findItem(R.id.nav_send);
//        menuItem.setVisible(false);// true 为显示，false 为隐藏

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Toast.makeText(MainActivity.this, "Import", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(MainActivity.this, "Gallery", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(MainActivity.this, "Slideshow", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_manage) {
            Toast.makeText(MainActivity.this, "Tools", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.Subltem1) {
            Intent intent = new Intent(this, BottomSheet.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//Activity切换动画要在APi21上才能使用
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            }
        } else if (id == R.id.Subltem2) {
            startActivity(new Intent(MainActivity.this, SwipRefreshLayoutActivity.class));
        } else if (id == R.id.Subltem3) {
            startActivity(new Intent(this,TintingTest.class));
        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);//点击Item后.关闭左边drawer，start和LEFT都为从左边出现
        //drawer.closeDrawers();若没有参数，则关闭所有打开的drawer
        return true;
    }
}
