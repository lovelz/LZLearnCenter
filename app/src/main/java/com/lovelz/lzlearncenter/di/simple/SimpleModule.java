package com.lovelz.lzlearncenter.di.simple;

import dagger.Module;

/**
 * @author lovelz
 * @date on 2019/2/18.
 */
@Module
public class SimpleModule {

    private SimpleStudentActivity simpleStudentActivity;

    public SimpleModule(SimpleStudentActivity simpleStudentActivity) {
        this.simpleStudentActivity = simpleStudentActivity;
    }

}
