package com.lovelz.lzlearncenter.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author lovelz
 * @date on 2018/9/19.
 */
public class CenterLineTextView extends AppCompatTextView {

    private Paint mPaint;

    public CenterLineTextView(Context context) {
        this(context, null);
    }

    public CenterLineTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CenterLineTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth() + getPaddingLeft() + getPaddingRight();
        int height = getHeight() + getPaddingTop() + getPaddingBottom();

        int startY = (int) ((height - mPaint.getStrokeWidth()) / 2);
        int endY = (int) ((height + mPaint.getStrokeWidth()) / 2);

        canvas.drawLine(0, startY, width, endY, mPaint);
    }
}
