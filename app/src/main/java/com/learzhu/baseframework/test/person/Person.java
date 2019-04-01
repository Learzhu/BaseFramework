package com.learzhu.baseframework.test.person;

/**
 * Person.java是液总汇的类。
 *
 * @author Learzhu
 * @version 2.0.0 2019-03-27 15:55
 * @update Learzhu 2019-03-27 15:55
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class Person {

    private String name;

//    @Inject
    public Person(String name) {
        this.name = name;
    }

    public Person() {
        System.out.println("a person created");
    }
}
