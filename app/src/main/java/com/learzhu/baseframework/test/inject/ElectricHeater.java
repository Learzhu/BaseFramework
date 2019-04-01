package com.learzhu.baseframework.test.inject;

import javax.inject.Inject;

/**
 * 电子加热器
 */
public class ElectricHeater implements Heater {
    //构造方法上 相当于new
    @Inject
    public ElectricHeater() {
    }

    boolean heating;

    @Override
    public void on() {
        System.out.println("~~~~heating~~~~");
        this.heating = true;
    }

    @Override
    public void off() {
        this.heating = false;
    }

    @Override
    public boolean isHot() {
        return heating;
    }
}
