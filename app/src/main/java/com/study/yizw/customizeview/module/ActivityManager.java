package com.study.yizw.customizeview.module;

import android.app.Activity;
import android.text.TextUtils;

import com.study.yizw.customizeview.util.LogUtil;

import java.util.LinkedList;

/**
 * ActivityManager
 * <p>
 * Created by yizw on 2016/9/20 16:41.
 */

public class ActivityManager {

    private static ActivityManager activityManager;

    private LinkedList<Activity> activityQueen;

    private String topActivityName;

    private static boolean isStartedHomePage;

    private ActivityManager() {
        activityQueen = new LinkedList<>();
    }

    public static ActivityManager getInstance() {
        if (activityManager == null) {
            synchronized (ActivityManager.class) {
                if (activityManager == null) {
                    activityManager = new ActivityManager();
                }
            }
        }
        return activityManager;
    }

    public static void markStartedHomePage() {
        isStartedHomePage = true;
    }

    public static boolean isStartedHomePage() {
        return isStartedHomePage;
    }

    /**
     * 设置顶部的activity
     * @param activity
     */
    public void setTopActivity(Activity activity) {
        topActivityName = activity.getClass().getName();
    }


    /**
     * 判断是否是顶层的activity
     * @param activityCls
     * @return
     */
    public boolean isTopActivity(Class<?> activityCls) {
        if (TextUtils.isEmpty(topActivityName)) {
            return false;
        }

        if (topActivityName.equals(activityCls.getName())) {
            return true;
        }

        return false;
    }

    private boolean checkActivityType(Activity activity) {
        boolean check = false;
        if (activity instanceof BaseActivity || activity instanceof BaseFragmentActivity) {
            check = true;
        }
        return check;
    }

    public synchronized void addActivity(Activity activity) {
        if (checkActivityType(activity)) {
            activityQueen.push(activity);
            LogUtil.i("add activity success:" + activity);
        } else {
            LogUtil.w("this activity manager do not add other type activity.");
        }
    }

    public synchronized void removeActivity(Activity activity) {
        if (checkActivityType(activity)) {
            if (!activityQueen.contains(activity)) {
                LogUtil.w("do not contains this type activity:" + activity);
                return;
            }
            if (activityQueen.remove(activity)) {
                LogUtil.i("remove activity success:" + activity);
            } else {
                LogUtil.e("remove activity fail:" + activity);
            }
        } else {
            LogUtil.w("this activity manager do not remove other type activity.");
        }
    }

    public synchronized Activity getTopActivity() {
        return activityQueen.peek();
    }

    public synchronized void finishAll() {
        try {
            Activity activity = null;
            while ((activity = activityQueen.poll()) != null) {
                LogUtil.i("finish activity:" + activity);
                activity.finish();
            }
            LogUtil.d("finish all activity.");
        } catch (Throwable e) {
            ToastUtil.createAndShow("catch activity finished error!");
            LogUtil.e(e);
        }
    }

    public synchronized boolean isActivityRunning(Class<?> activityCls) {
        for (Activity activity : activityQueen) {
            if (activity.getClass().getName().equals(activityCls.getName())) {
                return true;
            }
        }
        return false;
    }

    public synchronized void finishActivity(Class<?> activityCls) {
        Activity a = null;
        for (Activity activity : activityQueen) {
            if (activity.getClass().getName().equals(activityCls.getName())) {
                LogUtil.i("activity:" + activity.getClass().getName() + ",activityCls:" + activityCls.getName());
                a = activity;
                break;
            }
        }
        if (a != null) {
            LogUtil.i("remove activity:" + a);
            activityQueen.remove(a);
            a.finish();
        } else {
            LogUtil.w("no find this type activity:" + activityCls.getName());
        }
    }


}
