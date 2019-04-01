package com.learzhu.baseframework.test.person;

import com.learzhu.baseframework.test.AnnotionTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * PersonComponent.java是液总汇的类。
 *
 * @author Learzhu
 * @version 2.0.0 2019-03-27 15:58
 * @update Learzhu 2019-03-27 15:58
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
@Singleton
@Component(modules = {PersonModule.class})
public interface PersonComponent {
    void inject(AnnotionTest annotionTest);
}
