package com.learzhu.baseframework.test.test_annotion;

import dagger.Module;
import dagger.Provides;

/**
 * AModule.java是液总汇的类。
 *
 * @author Learzhu
 * @version 2.0.0 2019-03-28 13:43
 * @update Learzhu 2019-03-28 13:43
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
@Module
public class AModule {

    @Provides
    A providesA() {
        System.out.println("a crated from AModule");
        return new A();
    }
}
