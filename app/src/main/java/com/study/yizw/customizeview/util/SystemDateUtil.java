package com.study.yizw.customizeview.util;

import android.content.Context;
import android.os.SystemClock;

import java.util.Date;

/**
 * 日期工具类
 * <p>
 * Created by yizw on 2016/9/18.
 */

public class SystemDateUtil {

    private static long bootTime;

    private static Date initDate;

    private static long requestTime = 0;

    private static boolean isSuccess;

    private static boolean isRequesting;

    private SystemDateUtil() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void init(Context context) {
        if (isSuccess) {
            return;
        }

        if (isRequesting) {
            return;
        }

        isRequesting = true;
        bootTime = SystemClock.elapsedRealtime();
        initDate = new Date();
        getServerDateSync(context);
    }

    /**
     * 异步获取服务器当前时间
     *
     * @param context
     */
    private static void getServerDateSync(Context context) {



    }

    /**
     * 获取当前app运行时间
     *
     * @return
     */
    public static long getAppRunningTime() {
        long time = SystemClock.elapsedRealtime() - bootTime;
        return time;
    }

    /**
     * 获取当前app和服务器的同步时间
     *
     * @return
     */
    public static Date getCurrentDate() {
        if (initDate == null) {
            initDate = new Date();
        }
        long time = initDate.getTime() + getAppRunningTime();
        return new Date(time);
    }


}
