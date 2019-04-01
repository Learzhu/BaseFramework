package com.learzhu.baseframework.test.person;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * PersonModule.java是液总汇的类。
 *
 * @author Learzhu
 * @version 2.0.0 2019-03-27 15:57
 * @update Learzhu 2019-03-27 15:57
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
@Module
public class PersonModule {
    private String name;

    public PersonModule(String name) {
        this.name = name;
    }

    @Provides
    public String providesName() {
        return this.name;
    }

    @Singleton
    @Provides
    Person providePerson(String name) {
//        此处会调用providesName
        System.out.println("a person created from PersonModule");
        return new Person(name);
    }
}
