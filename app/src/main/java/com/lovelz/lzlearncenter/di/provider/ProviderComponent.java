package com.lovelz.lzlearncenter.di.provider;

import dagger.Component;

/**
 * @author lovelz
 * @date on 2019/2/21.
 */
@Component(modules = ProviderModule.class)
public interface ProviderComponent {

    void inject(ProviderActivity providerActivity);

}
