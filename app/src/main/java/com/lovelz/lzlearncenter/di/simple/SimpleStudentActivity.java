package com.lovelz.lzlearncenter.di.simple;

import android.util.Log;

import com.lovelz.lzlearncenter.BaseActivity;
import com.lovelz.lzlearncenter.R;

import javax.inject.Inject;

/**
 * @author lovelz
 * @date on 2019/2/18.
 */
public class SimpleStudentActivity extends BaseActivity {

    private static final String TAG = SimpleStudentActivity.class.getSimpleName();

    @Inject
    Student student;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_normal_layout;
    }

    @Override
    public void initEvent() {
        super.initEvent();

        DaggerSimpleComponent.builder()
                .build()
                .inject(this);

        Log.d(TAG, student.toString());
    }
}
