package com.lovelz.lzlearncenter.di.simple;

import dagger.Component;

/**
 * @author lovelz
 * @date on 2019/2/18.
 */
@Component
public interface SimpleComponent {

    void inject(SimpleStudentActivity simpleStudentActivity);

}
