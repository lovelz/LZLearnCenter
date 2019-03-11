package com.lovelz.lzlearncenter.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 流式布局
 *
 * @author lovelz
 * @date on 2018/9/20.
 */
public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //用于记录所有子view占用的最大宽高（当父view宽高没有设置具体数值时用这里的宽高）
        int maxWidth = 0;
        int maxHeight = 0;

        //用于记录每一行的宽高
        int lineWidth = 0;
        int lineHeight = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            //得到该view的宽高
            int childWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if ((lineWidth + childWidth) > (widthSize - getPaddingLeft() - getPaddingRight())) {
                //此时需要换行，记录之前的最大宽高
                maxWidth = Math.max(maxWidth, lineWidth);
                maxHeight += lineHeight;

                //重置lineWidth、lineHeight
                lineWidth = childWidth;
                lineHeight = childHeight;
            } else {
                //不需要换行
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }

            //最后一个view，之前不管换行没换行都没做任何处理，这里需单独处理
            if (i == (childCount - 1)) {
                maxWidth = Math.max(maxWidth, lineWidth);
                maxHeight += lineHeight;
            }
        }

        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? widthSize : (maxWidth + getPaddingLeft() + getPaddingRight()),
                (heightMode == MeasureSpec.EXACTLY) ? heightSize : (maxHeight + getPaddingTop() + getPaddingBottom()));
    }

    //用于保存每一行的view
    private List<List<View>> flowViewList = new ArrayList<>();
    //用于保存每一行的高度
    private List<Integer> flowHeightList = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        flowViewList.clear();
        flowHeightList.clear();

        //该view的宽度，在onMeasure()方法中我们已经设置过了
        int mWidth = getWidth();

        //记录行宽高，用于判断是否换行
        int lineWidth = 0;
        int lineHeight = 0;

        List<View> lineViews = new ArrayList<>();

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);

            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if ((childWidth + lineWidth) > (mWidth - getPaddingLeft() - getPaddingRight())) {
                //换行,需添加每一行的view、height
                flowViewList.add(lineViews);
                flowHeightList.add(lineHeight);

                lineWidth = 0;
                lineHeight = childHeight;

                //重新初始化
                lineViews = new ArrayList<>();
            }

            lineWidth += childWidth;
            lineHeight = Math.max(lineHeight, childHeight);
            lineViews.add(childView);

            //最后一个view特殊处理
            if (i == (childCount - 1)) {
                flowViewList.add(lineViews);
                flowHeightList.add(lineHeight);
            }
        }

        //初始化左、上边距
        int left = getPaddingLeft();
        int top = getPaddingTop();

        int lineCount = flowViewList.size();
        for (int i = 0; i < lineCount; i++) {
            lineViews = flowViewList.get(i);
            lineHeight = flowHeightList.get(i);

            for (int j = 0; j < lineViews.size(); j++) {
                View childView = lineViews.get(j);
                if (childView.getVisibility() == GONE) {
                    continue;
                }

                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();

                int childLeft = left + lp.leftMargin;
                int childTop = top + lp.topMargin;
                int childRight = childLeft + childView.getMeasuredWidth();
                int childBottom = childTop + childView.getMeasuredHeight();

                childView.layout(childLeft, childTop, childRight, childBottom);

                left += childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }

            left = getPaddingLeft();
            top += lineHeight;

        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

}
