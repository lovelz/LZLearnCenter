package com.lovelz.lzlearncenter.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lovelz.lzlearncenter.R;

/**
 * 自定义View
 *
 * @author lovelz
 * @date on 2018/9/19.
 */
public class CustomViewActivity extends AppCompatActivity {

    public static final String TAG = CustomViewActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_flow);
    }
}
