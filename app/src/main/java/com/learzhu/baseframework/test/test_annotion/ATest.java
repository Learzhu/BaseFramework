package com.learzhu.baseframework.test.test_annotion;

import javax.inject.Inject;

/**
 * ATest.java是液总汇的类。
 *
 * @author Learzhu
 * @version 2.0.0 2019-03-28 13:42
 * @update Learzhu 2019-03-28 13:42
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class ATest {
    @Inject
    A a;

    public static void main(String args[]) {
//        A a = new A();
        ATest aTest = new ATest();
        aTest.test();
    }

    public void test() {
        DaggerAComponent.builder().aModule(new AModule()).build().inject(this);
    }
}
