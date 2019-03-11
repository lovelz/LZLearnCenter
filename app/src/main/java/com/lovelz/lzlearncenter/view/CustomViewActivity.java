package com.lovelz.lzlearncenter.view;

import android.os.Bundle;

import com.lovelz.lzlearncenter.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 自定义View
 *
 * @author lovelz
 * @date on 2018/9/19.
 */
public class CustomViewActivity extends AppCompatActivity {

    public static final String TAG = CustomViewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_view);
    }
}
