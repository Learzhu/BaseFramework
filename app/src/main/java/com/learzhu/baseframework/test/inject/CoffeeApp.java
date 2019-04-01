package com.learzhu.baseframework.test.inject;

/**
 * 出咖啡测试
 */

/**
 * 通过DaggerPumpComponent.create().getPump();既可以得到Thermosiphon对象。
 * 我们看到在Thermosiphon中heater是以ElectricHeater来接收的，并没有向上转型为接口Heater引用，@Inject标记需要是确切的类型。等我们学习了下面@Provides丶@Module部分，我们就能间接的用Heater接口来接收。
 * 通过上面例子我们明显看到，我们并没有new 对象，然而却有ElectricHeater和Thermosiphon被实例化，这就是Dagger生成java代码中帮我们进行了实例化的操作。
 * 注意@Inject标记的成员变量、方法和构造方法不能以private修饰。因为如果我们用private，Dagger以java代码注入，自然不能帮我们注入对象，会报错!你可以试试看哦！(￣▽￣)~*
 * 也许你想问DaggerPumpComponent是哪来的？用@Component标记的接口会生成一个以“Dagger + 接口名”的类。
 * 经测试，如果将成员变量上的@Inject去掉，不会报错，只是没有引用的对象，变量为null。如果成员变量上标记了@Inject，而没有对应的注入实例，则会编译错误。
 * Dagger生成代码的位置：项目目录 -> app -> build -> generated -> source -> apt
 * 如果我们将@Inject标记在方法上，如果有参，Dagger提供该实例，然后自动调用该方法；如果无参则直接调用；如果有参没有对应的实例提供，则报错。
 */
public class CoffeeApp {
    public static void main(String[] args) {
        //可以得到Thermosiphon对象
        Thermosiphon pump = DaggerPumpComponent.create().getPump();
        pump.heater.on();
        pump.pump();
    }
}
