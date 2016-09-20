package com.study.yizw.customizeview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.OverScroller;

import com.study.yizw.customizeview.R;

import java.util.List;

/**
 * 横向滚轮
 *
 * Created by yizw on 2016/9/20 10:47.
 */

public class HorizontalWheelView extends View implements GestureDetector.OnGestureListener {
    public static final float DEFAULT_INTERVAL_FACTOR = 1.2f;
    public static final float DEFAULT_MARK_RATIO = 0.7f;

    private Paint mMarkPaint;
    private TextPaint mMarkTextPaint;
    private int mCenterIndex;

    private int mHighlightColor, mMarkTextColor;
    private int mMarkColor, mFadeMarkColor;

    private int mHeight;
    private List<String> mItems;
    private String mAdditionCenterMark;
    private OnWheelItemSelectedListener mOnWheelItemSelectedListener;
    private float mIntervalFactor = DEFAULT_INTERVAL_FACTOR;
    private float mMarkRatio = DEFAULT_MARK_RATIO;

    private int mMarkCount;
    private float mAdditionCenterMarkWidth;
    private float mCursorSize;
    private int mViewScopeSize;

    // scroll control args ---- start
    private OverScroller mScroller;
    private float mMaxOverScrollDistance;
    private RectF mContentRectF;
    private boolean mFling = false;
    private float mCenterTextSize, mNormalTextSize;
    private float mTopSpace, mBottomSpace;
    private float mIntervalDis;
    private float mCenterMarkWidth, mMarkWidth;
    private GestureDetectorCompat mGestureDetectorCompat;
    // scroll control args ---- end


    public HorizontalWheelView(Context context) {
        super(context);
        init(null);
    }

    public HorizontalWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public HorizontalWheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    protected void init(AttributeSet attrs) {
        float density = getResources().getDisplayMetrics().density;
        mCenterMarkWidth = (int) (density * 1.5f + 0.5f);
        mMarkWidth = density;

        mHighlightColor = 0xFFF74C39;
        mMarkTextColor = 0xFF666666;
        mMarkColor = 0xFFEEEEEE;
        mCursorSize = density * 18;
        mCenterTextSize = density * 22;
        mNormalTextSize = density * 18;
        mBottomSpace = density * 5;

        TypedArray ta = attrs == null ? null : getContext().obtainStyledAttributes(attrs, R.styleable.HorizontalWheelView);
        if (ta != null) {
            mHighlightColor = ta.getColor(R.styleable.HorizontalWheelView_HighlightColor, mHighlightColor);
            mMarkTextColor = ta.getColor(R.styleable.HorizontalWheelView_MarkTextColor, mMarkTextColor);
            mMarkColor = ta.getColor(R.styleable.HorizontalWheelView_MarkColor, mMarkColor);
            mIntervalFactor = ta.getFloat(R.styleable.HorizontalWheelView_IntervalFactor, mIntervalFactor);
            mMarkRatio = ta.getFloat(R.styleable.HorizontalWheelView_MarkRatio, mMarkRatio);
            mAdditionCenterMark = ta.getString(R.styleable.HorizontalWheelView_AdditionalCenterMark);
            mCenterTextSize = ta.getDimension(R.styleable.HorizontalWheelView_CenterMarkTextSize, mCenterTextSize);
            mNormalTextSize = ta.getDimension(R.styleable.HorizontalWheelView_MarkTextSize, mNormalTextSize);
            mCursorSize = ta.getDimension(R.styleable.HorizontalWheelView_CursorSize, mCursorSize);
        }
        mFadeMarkColor = mHighlightColor & 0xAAFFFFFF;
        mIntervalFactor = Math.max(1, mIntervalFactor);
        mMarkRatio = Math.min(1, mMarkRatio);
        //        mTopSpace = mCursorSize + density * 2;
        mTopSpace = mCursorSize;

        mMarkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMarkTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mMarkTextPaint.setTextAlign(Paint.Align.CENTER);
        mMarkTextPaint.setColor(mHighlightColor);

        mMarkPaint.setColor(mMarkColor);
        mMarkPaint.setStrokeWidth(mCenterMarkWidth);

        mMarkTextPaint.setTextSize(mCenterTextSize);
        calcIntervalDis();

        mScroller = new OverScroller(getContext());
        mContentRectF = new RectF();

        mGestureDetectorCompat = new GestureDetectorCompat(getContext(), this);

        selectIndex(0);
    }

    /**
     * calculate interval distance between items
     */
    private void calcIntervalDis() {
        if (mMarkTextPaint == null) {
            return;
        }
        String defaultText = "888888";
        Rect temp = new Rect();
        int max = 0;
        if (mItems != null && mItems.size() > 0) {
            for (String i : mItems) {
                mMarkTextPaint.getTextBounds(i, 0, i.length(), temp);
                if (temp.width() > max) {
                    max = temp.width();
                }
            }
        } else {
            mMarkTextPaint.getTextBounds(defaultText, 0, defaultText.length(), temp);
            max = temp.width();
        }

        if (!TextUtils.isEmpty(mAdditionCenterMark)) {
            mMarkTextPaint.setTextSize(mNormalTextSize);
            mMarkTextPaint.getTextBounds(mAdditionCenterMark, 0, mAdditionCenterMark.length(), temp);
            mAdditionCenterMarkWidth = temp.width();
            max += temp.width();
        }

        mIntervalDis = max * mIntervalFactor;
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
        int result = (int) (mBottomSpace + mTopSpace * 2 + mCenterTextSize);
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

    public void fling(int velocityX, int velocityY) {
        mScroller.fling(getScrollX(), getScrollY(), velocityX, velocityY, (int) -mMaxOverScrollDistance, (int) (mContentRectF.width() - mMaxOverScrollDistance), 0, 0, (int) mMaxOverScrollDistance, 0);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            mHeight = h;
            mMaxOverScrollDistance = w / 2.f;
            mContentRectF.set(0, 0, (mMarkCount - 1) * mIntervalDis, h);
            mViewScopeSize = (int) Math.ceil(mMaxOverScrollDistance / mIntervalDis);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int start = mCenterIndex - mViewScopeSize;
        int end = mCenterIndex + mViewScopeSize + 1;

        start = Math.max(start, -mViewScopeSize * 2);
        end = Math.min(end, mMarkCount + mViewScopeSize * 2);

        // extends both ends
        if (mCenterIndex == mMarkCount - 1) {
            end += mViewScopeSize;
        } else if (mCenterIndex == 0) {
            start -= mViewScopeSize;
        }

        float x = start * mIntervalDis;

        float markHeight = mHeight - mBottomSpace - mCenterTextSize - mTopSpace;
        // small scale Y offset
        float smallMarkShrinkY = markHeight * (1 - mMarkRatio) / 2f;
        smallMarkShrinkY = Math.min((markHeight - mMarkWidth) / 2f, smallMarkShrinkY);

        for (int i = start; i < end; i++) {
            float tempDis = mIntervalDis / 5f;
            // offset: Small mark offset Big mark
            for (int offset = -2; offset < 3; offset++) {
                float ox = x + offset * tempDis;

                if (i >= 0 && i <= mMarkCount && mCenterIndex == i) {
                    int tempOffset = Math.abs(offset);
                    if (tempOffset == 0) {
//                      mMarkPaint.setColor(mHighlightColor);
                        mMarkPaint.setColor(mMarkColor);
                    } else if (tempOffset == 1) {
//                      mMarkPaint.setColor(mFadeMarkColor);
                        mMarkPaint.setColor(mMarkColor);
                    } else {
                        mMarkPaint.setColor(mMarkColor);
                    }
                } else {
                    mMarkPaint.setColor(mMarkColor);
                }

                if (offset == 0) {
                    // center mark
                    mMarkPaint.setStrokeWidth(mCenterMarkWidth);
//                    canvas.drawLine(ox, mTopSpace / 2, ox, mTopSpace / 2 + markHeight, mMarkPaint);
                    canvas.drawLine(ox, 0, ox, markHeight, mMarkPaint);
                } else {
                    // other small mark
                    mMarkPaint.setStrokeWidth(mMarkWidth);
//                    canvas.drawLine(ox, mTopSpace + smallMarkShrinkY, ox, markHeight - smallMarkShrinkY, mMarkPaint);
                    canvas.drawLine(ox, mTopSpace / 2 + smallMarkShrinkY, ox, markHeight - smallMarkShrinkY - mTopSpace / 2, mMarkPaint);
                }
            }

            // mark text
            if (mMarkCount > 0 && i >= 0 && i < mMarkCount) {
                CharSequence temp = mItems.get(i);
                if (mCenterIndex == i) {
                    mMarkTextPaint.setColor(mHighlightColor);
                    mMarkTextPaint.setTextSize(mCenterTextSize);
                    if (!TextUtils.isEmpty(mAdditionCenterMark)) {
                        float off = mAdditionCenterMarkWidth / 2f;
                        float tsize = mMarkTextPaint.measureText(temp, 0, temp.length());
                        canvas.drawText(temp, 0, temp.length(), x - off, mHeight - mBottomSpace, mMarkTextPaint);
                        mMarkTextPaint.setTextSize(mNormalTextSize);
                        canvas.drawText(mAdditionCenterMark, x + tsize / 2f, mHeight - mBottomSpace, mMarkTextPaint);
                    } else {
                        canvas.drawText(temp, 0, temp.length(), x, mHeight - mBottomSpace / 2, mMarkTextPaint);
                    }
                } else {
                    mMarkTextPaint.setColor(mMarkTextColor);
                    mMarkTextPaint.setTextSize(mNormalTextSize);
                    canvas.drawText(temp, 0, temp.length(), x, mHeight - mBottomSpace / 2, mMarkTextPaint);
                }
            }

            x += mIntervalDis;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mItems == null || mItems.size() == 0) {
            return false;
        }
        boolean ret = mGestureDetectorCompat.onTouchEvent(event);
        if (!mFling && MotionEvent.ACTION_UP == event.getAction()) {
            if (getScrollX() < -mMaxOverScrollDistance) {
                mScroller.startScroll(getScrollX(), 0, (int) -mMaxOverScrollDistance - getScrollX(), 0);
                invalidate();
                ret = true;
            } else if (getScrollX() > mContentRectF.width() - mMaxOverScrollDistance) {
                mScroller.startScroll(getScrollX(), 0, (int) (mContentRectF.width() - mMaxOverScrollDistance) - getScrollX(), 0);
                invalidate();
                ret = true;
            } else {
                autoSettle();
                ret = true;
            }
        }
        return ret || super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            refreshCenter();
            invalidate();
        } else {
            if (mFling) {
                mFling = false;
                autoSettle();
            }
        }
    }

    public void setAdditionCenterMark(String additionCenterMark) {
        mAdditionCenterMark = additionCenterMark;
        calcIntervalDis();
        invalidate();
    }

    private void autoSettle() {
        int sx = getScrollX();
        float dx = mCenterIndex * mIntervalDis - sx - mMaxOverScrollDistance;
        mScroller.startScroll(sx, 0, (int) dx, 0);
        invalidate();
    }

    private void refreshCenter(int offsetX) {
        int offset = (int) (offsetX + mMaxOverScrollDistance);
        mCenterIndex = Math.round(offset / mIntervalDis);
        if (mCenterIndex < 0) {
            mCenterIndex = 0;
        } else if (mCenterIndex > mMarkCount - 1) {
            mCenterIndex = mMarkCount - 1;
        }
        if (null != mOnWheelItemSelectedListener) {
            mOnWheelItemSelectedListener.onWheelItemSelected(mCenterIndex);
        }
    }

    private void refreshCenter() {
        refreshCenter(getScrollX());
    }

    public void selectIndex(int index) {
        mCenterIndex = index;
        post(new Runnable() {
            @Override
            public void run() {
                scrollTo((int) (mCenterIndex * mIntervalDis - mMaxOverScrollDistance), 0);
                invalidate();
                refreshCenter();
            }
        });
    }

    public void smoothSelectIndex(int index) {
        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();
        }
        int deltaIndex = index - mCenterIndex;
        mScroller.startScroll(getScrollX(), 0, (int) (deltaIndex * mIntervalDis), 0);
        invalidate();
    }

    public List<String> getItems() {
        return mItems;
    }

    public void setItems(List<String> items) {
        mItems = items;
        mMarkCount = null == mItems ? 0 : mItems.size();
        mCenterIndex = Math.min(mCenterIndex, mMarkCount);
        calcIntervalDis();
        invalidate();
    }

    public int getSelectedPosition() {
        return mCenterIndex;
    }

    public void setOnWheelItemSelectedListener(OnWheelItemSelectedListener onWheelItemSelectedListener) {
        mOnWheelItemSelectedListener = onWheelItemSelectedListener;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        if (!mScroller.isFinished()) {
            mScroller.forceFinished(false);
        }
        mFling = false;
        if (null != getParent()) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        playSoundEffect(SoundEffectConstants.CLICK);
        refreshCenter((int) (getScrollX() + e.getX() - mMaxOverScrollDistance));
        autoSettle();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        float dis = distanceX;
        float scrollX = getScrollX();
        if (scrollX < 2 * -mMaxOverScrollDistance) {
            dis = 0;
        } else if (scrollX < -mMaxOverScrollDistance) {
            dis = distanceX / 4.f;
        } else if (scrollX > mContentRectF.width()) {
            dis = 0;
        } else if (scrollX > mContentRectF.width() - mMaxOverScrollDistance) {
            dis = distanceX / 4.f;
        }
        scrollBy((int) dis, 0);
        refreshCenter();
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float scrollX = getScrollX();
        if (scrollX < -mMaxOverScrollDistance || scrollX > mContentRectF.width() - mMaxOverScrollDistance) {
            return false;
        } else {
            mFling = true;
            fling((int) -velocityX, 0);
            return true;
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.index = getSelectedPosition();
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        selectIndex(ss.index);
        requestLayout();
    }

    static class SavedState extends BaseSavedState {
        int index;
        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            index = (int) in.readValue(null);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeValue(index);
        }

        @Override
        public String toString() {
            return "WheelView.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " index=" + index + "}";
        }
    }

    public interface OnWheelItemSelectedListener {
        void onWheelItemSelected(int position);
    }
}