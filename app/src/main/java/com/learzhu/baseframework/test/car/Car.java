package com.learzhu.baseframework.test.car;

import javax.inject.Inject;

/**
 * Car.java是液总汇的类。
 *
 * @author Learzhu
 * @version 2.0.0 2019-03-28 11:00
 * @update Learzhu 2019-03-28 11:00
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class Car {
    private String name;

    @Inject
    public Car(String name) {
        this.name = name;
    }

    public Car() {
        System.out.println("car created");
    }
}
