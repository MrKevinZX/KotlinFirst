package com.bmsr.tree.lambda;

/**
 * @Autohr ： yby
 * @CreateDate : 2020-02-01 09:43
 * @Description :
 */
public class JavaLambda {

    TestInterface testInterface;

    public JavaLambda(TestInterface testInterface) {
        this.testInterface = testInterface;
    }


    public void setTestInterface(TestInterface testInterface) {
        this.testInterface = testInterface;
    }

    interface TestInterface{
        void testmethod();
    }

    interface TestInterface2 {
        void testMethod(String params);
    }
}
