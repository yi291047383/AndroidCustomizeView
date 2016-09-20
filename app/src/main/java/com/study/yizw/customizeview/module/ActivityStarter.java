package com.study.yizw.customizeview.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * New Class
 * <p>
 * Created by yizw on 2016/9/20 16:50.
 */

public class ActivityStarter {


    public static void startActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }


    public static void startActivityAnimator(Activity activity, int enterAnim, int exitAnim) {
        activity.overridePendingTransition(enterAnim, exitAnim);
    }

    public static void startEnterAnimator(Activity activity) {

        startActivityAnimator(activity, R.anim.activity_in_effect, R.anim.activity_none_effect);
    }



    /**
     * 主界面
     *
     * @param context
     */
    public static void startHomePageActivity(Context context) {
        startActivity(context, HomePageActivity.class);
    }



}
