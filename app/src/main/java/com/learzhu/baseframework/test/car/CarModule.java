package com.learzhu.baseframework.test.car;

import dagger.Module;
import dagger.Provides;

/**
 * CarModule.java是液总汇的类。
 *
 * @author Learzhu
 * @version 2.0.0 2019-03-28 11:00
 * @update Learzhu 2019-03-28 11:00
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
@Module
public class CarModule {

    private String name;

    public CarModule(String name) {
        this.name = name;
    }

    @Provides
    public String provideName() {
        return name;
    }

    @Provides
    Car provideCar() {
        System.out.println("car created ");
        return new Car();
    }
}
