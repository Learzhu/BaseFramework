package com.learzhu.baseframework.test.test_annotion;

import dagger.Component;

/**
 * AComponentj.java是液总汇的类。
 *
 * @author Learzhu
 * @version 2.0.0 2019-03-28 13:44
 * @update Learzhu 2019-03-28 13:44
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
@Component(modules = {AModule.class})
public interface AComponent {
    void inject(ATest aTest);
}
