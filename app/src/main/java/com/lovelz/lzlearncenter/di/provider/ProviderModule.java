package com.lovelz.lzlearncenter.di.provider;

import dagger.Module;
import dagger.Provides;

/**
 * @author lovelz
 * @date on 2019/2/21.
 */
@Module
public class ProviderModule {

    @Provides
    public Student providerStudent() {
        return new Student();
    }

}
