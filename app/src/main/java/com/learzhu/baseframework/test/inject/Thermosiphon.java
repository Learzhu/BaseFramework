package com.learzhu.baseframework.test.inject;


import javax.inject.Inject;

/**
 * 热虹吸
 */
public class Thermosiphon implements Pump {
    //@Inject标记在成员变量上，表示其他地方需要提供ElectricHeater对象
    //heater是以ElectricHeater来接收的，并没有向上转型为接口Heater引用，@Inject标记需要是确切的类型
    @Inject
    ElectricHeater heater;

    @Inject
    public void funTest() {
        System.out.println("funTest()");
    }

    @Inject
    public void funTest(ElectricHeater heater) {
        System.out.println("heater: " + this.heater.hashCode());
        System.out.println("funTest(): " + heater.hashCode());
    }

    //@Inject标记在构造方法上，可以看做new Thermosiphon()
    @Inject
    public Thermosiphon() {

    }

    @Override
    public void pump() {
        if (heater.isHot()) {
            System.out.println("=>=> 抽水 =>=>");
        }
    }
}
