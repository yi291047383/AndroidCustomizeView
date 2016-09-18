package com.study.yizw.customizeview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.study.yizw.customizeview.R;
import com.study.yizw.customizeview.util.LogUtil;

/**
 * 绘制三角形View
 *
 * Created by yizw on 2016/9/18.
 */

public class TriangleView extends View {

    private int mHighlightColor;

    private Paint mMarkPaint;
    private Path mCenterIndicatorPath = new Path();
    private float mCursorSize;

    private float mTopSpace, mBottomSpace;
    private float mCenterMarkWidth;

    public TriangleView(Context context) {
        super(context);
        init(null);
    }

    public TriangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TriangleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    protected void init(AttributeSet attrs) {
        float density = getResources().getDisplayMetrics().density;
        mCenterMarkWidth = (int) (density * 1.5f + 0.5f);

        mHighlightColor = 0xd924a8d8;
        mCursorSize = density * 18;
        mBottomSpace = density * 6;
        LogUtil.e("------mCursorSize------>"+mCursorSize);

        TypedArray ta = attrs == null ? null : getContext().obtainStyledAttributes(attrs, R.styleable.TriangleView);
        if (ta != null) {
            mHighlightColor = ta.getColor(R.styleable.TriangleView_ViewColor, mHighlightColor);
            mCursorSize = ta.getDimension(R.styleable.TriangleView_ViewCursorSize, mCursorSize);
        }
        mTopSpace = mCursorSize + density * 2;
        mMarkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMarkPaint.setStrokeWidth(mCenterMarkWidth);

        LogUtil.e("------mCursorSize------>"+mCursorSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthMeasureSpec) {
        int measureMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureSize = MeasureSpec.getSize(widthMeasureSpec);
        int result = getSuggestedMinimumWidth();
        switch (measureMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = measureSize;
                break;
            default:
                break;
        }
        return result;
    }

    private int measureHeight(int heightMeasure) {
        int measureMode = MeasureSpec.getMode(heightMeasure);
        int measureSize = MeasureSpec.getSize(heightMeasure);
        int result = (int) (mBottomSpace + mTopSpace * 2);
        switch (measureMode) {
            case MeasureSpec.EXACTLY:
                result = Math.max(result, measureSize);
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(result, measureSize);
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mCenterIndicatorPath.reset();
        float sizeDiv = mCursorSize * 2;

        mCenterIndicatorPath.moveTo(getScreenWidth() / 2.f - sizeDiv + getScrollX(), 0);
        mCenterIndicatorPath.rLineTo(sizeDiv, sizeDiv);
        mCenterIndicatorPath.rLineTo(sizeDiv, -sizeDiv);
        mCenterIndicatorPath.close();

        mMarkPaint.setColor(mHighlightColor);
        canvas.drawPath(mCenterIndicatorPath, mMarkPaint);
    }

    /**
     * 得到屏幕宽度
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 设置颜色
     *
     * @param color
     */
    public void setColor(int color) {
        this.mHighlightColor = color;
    }

}



