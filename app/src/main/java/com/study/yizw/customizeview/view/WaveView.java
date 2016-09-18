package com.study.yizw.customizeview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 波浪特效
 *
 * Created by yizw on 2016/9/18.
 */

public class WaveView extends View {

    private Paint paint;
    private int maxWidth = 255;
    private int color = 0x99ff8534;
    private int type;
    private boolean isStarting = false; // 是否在运行
    private List<String> alphaList = new ArrayList<String>();
    private List<String> startWidthList = new ArrayList<String>();

    private boolean isRelease = false;

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(color);  // 设置颜色
//      alphaList.add("255");         // 圆心的不透明度
        if(type == 0){
            alphaList.add("255");
        }else{
            alphaList.add("135");
        }
        startWidthList.add("0");
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isRelease) {
            canvas.restore();
            alphaList.clear();
            startWidthList.clear();
            init();
            isRelease = false;
            return;
        }

        setBackgroundColor(Color.TRANSPARENT);  // 颜色：完全透明

        // 依次绘制 同心圆
        for (int i = 0; i < alphaList.size(); i++) {
            int alpha = Integer.parseInt(alphaList.get(i));
            // 圆半径
            int startWidth = Integer.parseInt(startWidthList.get(i));
            paint.setAlpha(alpha);

            // 这个半径决定你想要多大的扩散面积
            if(type == 0){
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, startWidth + 80, paint);
            }else{
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, startWidth, paint);
            }

            // 同心圆扩散
            if (isStarting && alpha > 0) {
                alphaList.set(i, (alpha - 1) + "");
            }

            if (isStarting && alpha > 0 && startWidth < maxWidth) {
                alphaList.set(i, (alpha - 1) + "");
                startWidthList.set(i, (startWidth + 1) + "");
            }
        }

        if (isStarting && Integer
                .parseInt(startWidthList.get(startWidthList.size() - 1)) == maxWidth / 5 ) {
            //            alphaList.add("255");
            if(type == 0){
                alphaList.add("255");
            }else{
                alphaList.add("135");
            }
            startWidthList.add("0");
        }

        // 同心圆数量达到6个，删除最外层圆
        if (isStarting && startWidthList.size() == 6) {
            startWidthList.remove(0);
            alphaList.remove(0);
        }

        // 刷新界面
        invalidate();
    }

    // 执行动画
    public void start() {
        isStarting = true;
    }

    public void startWaveAnimation(int mWidth,int mColor,int mType){
        maxWidth = mWidth;
        color = mColor;
        type = mType;
        init();
        isStarting = true;
    }

    // 停止动画
    public void stop() {
        isStarting = false;
    }

    public void release() {
        isRelease = true;
    }

    // 判断是都在不在执行
    public boolean isStarting() {
        return isStarting;
    }
}
