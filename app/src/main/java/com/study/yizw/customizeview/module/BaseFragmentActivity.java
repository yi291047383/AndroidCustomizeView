package com.study.yizw.customizeview.module;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

/**
 * Fragment基础类
 * <p>
 * Created by yizw on 2016/9/21 20:29.
 */

public class BaseFragmentActivity extends FragmentActivity {

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
    }

//    @Override
//    public void finishActivity(int requestCode) {
//        ActivityStarter.startExitAnimator(this);
//        super.finish();
//    }

    @Override
    public void finish() {
        super.finish();
//        ActivityStarter.startExitAnimator(this);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        ActivityStarter.startEnterAnimator(this);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        ActivityStarter.startEnterAnimator(this);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.fontScale = 1;
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
