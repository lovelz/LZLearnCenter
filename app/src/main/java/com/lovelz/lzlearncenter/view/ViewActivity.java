package com.lovelz.lzlearncenter.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lovelz.lzlearncenter.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author lovelz
 * @date on 2018/9/18.
 */
public class ViewActivity extends AppCompatActivity {

    public static final String TAG = ViewActivity.class.getSimpleName();
    private MovableView movableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view);

        movableView = findViewById(R.id.movable_view);

//        movableView.smoothScroll(600, 800);

        movableView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                translateAnimator();
                Log.d(TAG, "---->setOnClickListener");

//                rotateAnimator();
            }
        });

        movableView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "---->setOnTouchListener");
                return true;
            }
        });
    }

    /**
     * 平移动画
     */
    private void translateAnimator() {
        ObjectAnimator translateAnim = ObjectAnimator.ofFloat(movableView, "translationX", 400);
        translateAnim.setDuration(600);
        translateAnim.start();

        //监听数值变化
        translateAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d(TAG, "onAnimationUpdate-->  " + animation.getAnimatedValue());
            }
        });
    }

    /**
     * 旋转动画
     */
    private void rotateAnimator() {
        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(movableView, "rotation", 360);
        rotateAnim.setDuration(600);
        rotateAnim.start();
    }
}
