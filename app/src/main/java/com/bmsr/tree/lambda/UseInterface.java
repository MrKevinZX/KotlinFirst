package com.bmsr.tree.lambda;

/**
 * @Autohr ： yby
 * @CreateDate : 2020-02-01 09:45
 * @Description :
 */
public class UseInterface {
    private void test1() {
        JavaLambda javaLambda = new JavaLambda(new JavaLambda.TestInterface() {
            @Override
            public void testmethod() {
                System.out.printf("test method");
            }
        });
        JavaLambda.TestInterface javaLambda1 = () -> {System.out.println("test method 1");};

    }


    public void test2() {
        JavaLambda.TestInterface2 testInterface2 = (s1) -> {
            System.out.printf("s1 =" + s1);
        };
        JavaLambda.TestInterface2 testInterface21 = s1 -> System.out.printf("test 2 = " + s1);
    }

}
