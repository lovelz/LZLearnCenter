package com.lovelz.lzlearncenter.di;

import android.content.Intent;
import android.view.View;

import com.lovelz.lzlearncenter.BaseActivity;
import com.lovelz.lzlearncenter.R;
import com.lovelz.lzlearncenter.di.simple.SimpleStudentActivity;

/**
 * @author lovelz
 * @date on 2019/2/18.
 */
public class DiTestActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public void initEvent() {
        super.initEvent();
        findViewById(R.id.enter_simple).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.enter_simple:
                intent = new Intent(this, SimpleStudentActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_di_test;
    }
}
