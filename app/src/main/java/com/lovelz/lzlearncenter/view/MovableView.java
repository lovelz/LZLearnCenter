package com.lovelz.lzlearncenter.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.Nullable;

/**
 * @author lovelz
 * @date on 2018/9/18.
 */
public class MovableView extends View {

    private int downX;
    private int downY;

    private Scroller mScroller;

    public MovableView(Context context) {
        this(context, null);
    }

    public MovableView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MovableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //按下
                downX = (int) event.getX();
                downY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //移动
                int offsetX = x - downX;
                int offsetY = y - downY;

                //使用layout()移动
//                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);

                //使用layout params移动
//                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) getLayoutParams();
//                lp.leftMargin = getLeft() + offsetX;
//                lp.topMargin = getTop() + offsetY;
//                setLayoutParams(lp);

                //scrollBy
//                ((View)getParent()).scrollBy(-offsetX, -offsetY);
//                Log.d(ViewActivity.TAG, "getLeft-->  " + getLeft() + "  getTop-->  " + getTop());
                break;
            case MotionEvent.ACTION_UP:
                //移开
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            ((View)getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    /**
     * 平滑滚动
     * @param destX
     * @param destY
     */
    public void smoothScroll(int destX, int destY) {
        int scrollX = getScrollX();
        int deltaX = scrollX - destX;
        int scrollY = getScrollY();
        int deltaY = scrollY - destY;
        mScroller.startScroll(scrollX, scrollY, deltaX, deltaY, 5000);
        invalidate();
    }
}
