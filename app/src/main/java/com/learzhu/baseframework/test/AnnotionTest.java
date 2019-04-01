package com.learzhu.baseframework.test;

import com.learzhu.baseframework.test.car.CarModule;
import com.learzhu.baseframework.test.car.DaggerCarComponent;
import com.learzhu.baseframework.test.person.DaggerPersonComponent;
import com.learzhu.baseframework.test.person.Person;
import com.learzhu.baseframework.test.person.PersonModule;

import javax.inject.Inject;

/**
 * AnnotionTest.java是液总汇的类。
 *
 * @author Learzhu
 * @version 2.0.0 2019-03-27 15:55
 * @update Learzhu 2019-03-27 15:55
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class AnnotionTest {
    @Inject
    Person person;
    @Inject
    Person person1;

    public static void main(String args[]) {
//        person = new Person();
        AnnotionTest annotionTest = new AnnotionTest();
        annotionTest.test();
    }

    public void test() {
        DaggerPersonComponent.builder().personModule(new PersonModule("aa")).build().inject(this);
        System.out.println(person);
        System.out.println(person1);
    }

    public void testCar() {
        DaggerCarComponent.builder().carModule(new CarModule("car")).build();
    }
}
