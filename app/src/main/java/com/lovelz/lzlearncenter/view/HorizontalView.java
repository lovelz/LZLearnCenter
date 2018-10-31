package com.lovelz.lzlearncenter.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 水平滑动view，类似viewpager
 *
 * @author lovelz
 * @date on 2018/9/19.
 */
public class HorizontalView extends ViewGroup {

    private Scroller mScroller;

    //滑动速度检测
    private VelocityTracker tracker;

    //用于记录onInterceptTouchEvent()事件中的坐标
    private int lastInterceptX;
    private int lastInterceptY;

    //记录点击事件相关的坐标
    private int lastX;
    private int lastY;

    private int currentChildWidth = 0;
    private int currentIndex = 0;

    public HorizontalView(Context context) {
        this(context, null);
    }

    public HorizontalView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        tracker = VelocityTracker.obtain();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            //宽高都为wrap_content,默认宽度为所有子view宽度的和（子view宽度都为一整个屏幕），高度为第一个view的高
            View childView = getChildAt(0);
            int childWidth = childView.getMeasuredWidth();
            setMeasuredDimension(childWidth * childCount, childView.getHeight());
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //宽度为wrap_content
            View childView = getChildAt(0);
            int childWidth = childView.getMeasuredWidth();
            setMeasuredDimension(childWidth * childCount, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //高度为wrap_content
            setMeasuredDimension(widthSize, getChildAt(0).getMeasuredHeight());
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() != GONE) {
                int width = childView.getMeasuredWidth();
                currentChildWidth = width;
                childView.layout(left, 0, left + width, childView.getMeasuredHeight());
                left += width;
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastInterceptX;
                int deltaY = y - lastInterceptY;

                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    intercept = true;
                } else {
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }

        lastInterceptX = x;
        lastInterceptY = y;

        lastX = x;
        lastY = y;
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        tracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;
                scrollBy(-deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(CustomViewActivity.TAG, "getScrollX-->" + getScrollX());
                int distance = getScrollX() - currentChildWidth * currentIndex;
                if (Math.abs(distance) > currentChildWidth / 2) {
                    if (distance > 0) {
                        currentIndex ++;
                    } else {
                        currentIndex --;
                    }
                } else {
                    tracker.computeCurrentVelocity(1000);
                    float xV = tracker.getXVelocity();
                    Log.d(CustomViewActivity.TAG, "xV-->  " + xV);
                    if (Math.abs(xV) > 50) {
                        if (xV > 0) {
                            currentIndex --;
                        } else {
                            currentIndex ++;
                        }
                    }
                }

                currentIndex = currentIndex < 0 ? 0 : currentIndex > (getChildCount() - 1) ? (getChildCount() - 1) : currentIndex;
                smoothScrollTo(currentIndex * currentChildWidth, 0);

                //清除加速检测
                tracker.clear();
                break;
        }

        lastX = x;
        lastY = y;
        return true;
    }

    /**
     * 平滑滚动
     * @param destX 水平距离
     * @param destY 垂直距离
     */
    private void smoothScrollTo(int destX, int destY) {
        mScroller.startScroll(getScrollX(), getScrollY(), destX - getScrollX(), destY - getScrollY(), 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
