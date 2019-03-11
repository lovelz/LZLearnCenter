package com.lovelz.lzlearncenter.di.provider;

import com.lovelz.lzlearncenter.BaseActivity;
import com.lovelz.lzlearncenter.R;

import javax.inject.Inject;

/**
 * @author lovelz
 * @date on 2019/2/21.
 */
public class ProviderActivity extends BaseActivity {

    @Inject
    Student student;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_normal_layout;
    }

    @Override
    public void initEvent() {
        super.initEvent();

        DaggerProviderComponent
                .builder()
                .providerModule(new ProviderModule())
                .build()
                .inject(this);
    }
}
