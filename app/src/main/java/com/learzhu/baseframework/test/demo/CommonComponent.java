package com.learzhu.baseframework.test.demo;

import dagger.Component;

/**
 * CommonComponent.java是液总汇的类。
 *
 * @author Learzhu
 * @version 2.0.0 2019-04-08 14:37
 * @update Learzhu 2019-04-08 14:37
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
@ActivityScope
@Component(modules = CommonModule.class)
public interface CommonComponent {
    void inject(LoginActivity activity);
}
