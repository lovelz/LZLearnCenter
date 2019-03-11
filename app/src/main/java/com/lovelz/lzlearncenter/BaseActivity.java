package com.lovelz.lzlearncenter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author lovelz
 * @date on 2019/2/18.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        initEvent();
    }

    public void initEvent() {}

    protected abstract int getLayoutId();
}
