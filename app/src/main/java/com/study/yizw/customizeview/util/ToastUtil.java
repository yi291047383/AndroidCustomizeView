package com.study.yizw.customizeview.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.study.yizw.customizeview.service.CodeWapper;
import com.study.yizw.customizeview.service.ErrorCodeDescribe;

/**
 * ToastUtil
 * <p>
 * Created by yizw on 2016/9/20 16:43.
 */

public class ToastUtil {

    private static Context context;

    private static Toast toast;

    public static void init(Context ctx) {
        if (context == null) {
            context = ctx.getApplicationContext();
        }
        if (toast == null) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
            } else {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
                    }
                });
            }
        }
    }

    private static void showToast(final Toast toast, final String text) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            toast.setText(text);
            toast.show();
        } else {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    toast.setText(text);
                    toast.show();
                }
            });
        }
    }

    public static void show(String tag, CodeWapper codeWapper) {
        String text = ErrorCodeDescribe.getDescribe(context, tag, codeWapper);
        show(text);
    }

    public static void show(CodeWapper codeWapper) {
        String text = ErrorCodeDescribe.getDescribe(context, codeWapper);
        show(text);
    }

    public static void show(String text) {
        showToast(toast, text);
    }

    public static void show(int resId) {
        String text = context.getString(resId);
        show(text);
    }

    public static void show(String text, int duration) {
        toast.setDuration(duration);
        show(text);
    }

    public static void show(int resId, int duration) {
        String text = context.getString(resId);
        show(text, resId);
    }

    public static void createAndShow(String text) {
        createAndShow(text, Toast.LENGTH_SHORT);
    }

    public static void createAndShow(int resId) {
        String text = context.getString(resId);
        createAndShow(text);
    }

    public static void createAndShow(final String text, final int duration) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(context, text, duration).show();
        } else {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, text, duration).show();
                }
            });
        }
    }

    public static void createAndShow(int resId, int duration) {
        String text = context.getString(resId);
        createAndShow(text, duration);
    }

    public static void cancel() {
        if (toast != null) {
            toast.cancel();
        }
    }

    public static void destroy() {
        if (toast != null) {
            toast.cancel();
        }
        toast = null;
        context = null;
    }


}
