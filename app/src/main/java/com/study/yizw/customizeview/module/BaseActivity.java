package com.study.yizw.customizeview.module;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.yizw.customizeview.R;

import java.util.List;

/**
 * Activity基础类
 *
 * Created by yizw on 2016/9/20.
 */

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //禁止横屏切换
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityManager.getInstance().setTopActivity(this);
        onPageResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPagePause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
        System.gc();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_BACKGROUND) {
            System.gc();
        } else if (level == TRIM_MEMORY_UI_HIDDEN) {
            System.gc();
        }
    }

    public void back(View view) {
        finish();
    }

    public void setActivityTitle(int titleResId) {
        TextView titleView = (TextView) findViewById(R.id.navigate_title_tv);
        titleView.setText(titleResId);
    }

    public void hideBackButton() {
        ImageView imageView = (ImageView) findViewById(R.id.navigate_back_btn);
        imageView.setVisibility(View.GONE);
        TextView tv = (TextView) findViewById(R.id.navigate_back_text);
        tv.setVisibility(View.GONE);
    }

//    @Override
//    public void finishActivity(int requestCode) {
//        ActivityStarter.startExitAnimator(this);
//        super.finish();
//    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void startActivity(Intent intent) {

        if (isIntentAvailable(intent)) {
            super.startActivity(intent);
            ActivityStarter.startEnterAnimator(this);
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (isIntentAvailable(intent)) {
            super.startActivityForResult(intent, requestCode);
            ActivityStarter.startEnterAnimator(this);
        }
    }

    private boolean isIntentAvailable(Intent intent) {
        if (intent == null) {
            return false;
        }
        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.GET_ACTIVITIES);
        return list.size() > 0;
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.fontScale = 1; //控制不同手机上的字体大小都是一样的
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    /**
     * 子类如果要实现自己的页面统计就重载此方法
     */
    protected void onPageResume() {


    }

    /**
     * 与onPageResume配对出现,线性闭合
     */
    protected void onPagePause() {


    }




}
