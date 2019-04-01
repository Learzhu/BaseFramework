package com.learzhu.baseframework.test.car;

import javax.inject.Singleton;

import dagger.Component;

/**
 * CarComponent.java是液总汇的类。
 *
 * @author Learzhu
 * @version 2.0.0 2019-03-28 11:02
 * @update Learzhu 2019-03-28 11:02
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
@Singleton
@Component(modules = {CarModule.class})
public interface CarComponent {
    //    void inject(AnnotionTest annotionTest);
    //向下层提供Name
    String getName();
}
