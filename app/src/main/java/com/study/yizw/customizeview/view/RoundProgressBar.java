package com.study.yizw.customizeview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.study.yizw.customizeview.R;

/**
 * 环形间隔进度条
 *
 * Created by yizw on 2016/9/18.
 */

public class RoundProgressBar extends View {
    private float startAngle = 135;
    private float sweepAngle = 270;


    /**
     * 画笔对象的引用
     */
    private Paint paint;

    /**
     * 圆环的颜色
     */
    private int roundColor;

    /**
     * 圆环进度的颜色
     */
    private int roundProgressColor;

    /**
     * 中间进度百分比的字符串的颜色
     */
    private int textColor;

    /**
     * 中间进度百分比的字符串的字体
     */
    private float textSize;

    /**
     * 圆环的宽度
     */
    private float roundWidth;

    /**
     * 圆环被分割的份数
     */
    private float roundParts;

    /**
     * 最大进度
     */
    private int max;

    /**
     * 当前进度
     */
    private int progress;


    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        paint = new Paint();

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);

        // 获取自定义属性和默认值
        roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.RED);
        roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
        textColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressTextColor, Color.GREEN);
        textSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundProgressTextSize, 15);
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 5);
        roundParts = mTypedArray.getInteger(R.styleable.RoundProgressBar_roundParts, 50);
        max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);

        mTypedArray.recycle();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 画最外层的大圆环
         */
        int centre = getWidth() / 2; // 获取圆心的x坐标
        int radius = (int) (centre - roundWidth / 2); // 圆环的半径
        paint.setColor(roundColor); // 设置圆环的颜色
        paint.setStrokeWidth(roundWidth); // 设置圆环的宽度
        paint.setAntiAlias(true); // 消除锯齿
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius); // 用于定义的圆弧的形状和大小的界限
        float parts = 360.0f / (roundParts * 2);
        Log.e("---1-", "-----parts------->" + parts);  //3.6

        paint.setStyle(Paint.Style.STROKE); // 设置空心
        for (int i = 0; i < roundParts * 2; i++) {
            if (0 == i % 2) {
                if (parts * i > startAngle && parts * i < (startAngle + sweepAngle) || (parts * i < 45)) {
                    canvas.drawArc(oval, parts * i, (parts * 15) / 10, false, paint); // 根据进度画圆弧
                }
            }
        }


        /**
         * 画圆弧 ，画圆环的进度
         */
        paint.setStrokeWidth(roundWidth); // 设置圆环的宽度
        paint.setColor(roundProgressColor); // 设置进度的颜色
        float arcLength = ((270.0f) * progress / max)  + 135;

        paint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < arcLength / parts; i++) {
            if (0 == i % 2) {
                if (i == (int) (arcLength / parts) && (int) (arcLength / parts) * parts < 360) {
                    canvas.drawArc(oval, i * parts, arcLength - parts * i, false, paint);
                } else {
                    if (parts * i > startAngle && parts * i < 360) {
                        Log.e("----", "-----parts * i------->" + parts * i);
                        Log.e("----", "-----i------->" + i);
                        canvas.drawArc(oval, parts * i, (parts * 15) / 10, false, paint); // 根据进度画圆弧
                    }
                }
            }
        }


        if (progress > 83.3) {
            float arcLength2 = 360.0f * (progress - 83.3f) / max;
            Log.e("----", "-----arcLength2------->" + arcLength2);
            for (int i = 0; i < arcLength2 / parts; i++) {
                if (0 == i % 2) {
                    if (i == (int) (arcLength2 / parts) && parts * i <= 45) {
                        canvas.drawArc(oval, i * parts, arcLength2 - parts * i, false, paint);
                    } else {
                        if (parts * i < 45) {
                            canvas.drawArc(oval, parts * i, (parts * 15) / 10, false, paint); // 根据进度画圆弧
                        }
                    }
                }
            }
        }

    }

    public synchronized int getMax() {
        return max;
    }

    /**
     * 设置进度的最大值
     *
     * @param max
     */
    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }

    /**
     * 获取进度.需要同步
     *
     * @return
     */
    public synchronized int getProgress() {
        return progress;
    }

    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步 刷新界面调用postInvalidate()能在非UI线程刷新
     *
     * @param progress
     */
    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }

    }

    public int getCricleColor() {
        return roundColor;
    }

    public void setCricleColor(int cricleColor) {
        this.roundColor = cricleColor;
    }

    public int getCricleProgressColor() {
        return roundProgressColor;
    }

    public void setCricleProgressColor(int cricleProgressColor) {
        this.roundProgressColor = cricleProgressColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }



}

