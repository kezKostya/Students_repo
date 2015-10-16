package com.students;

import org.junit.Test;

/**
 * Created by kkolesnichenko on 10/16/2015.
 */
public class LambdaTest {

    public static class Person {

        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public interface MyFunctionalInterface {
        boolean apply();
    }

    public static void doPhilosofize(String string, MyFunctionalInterface myInterface){
        if(myInterface.apply()){
            System.out.println(string);
        }
    }

    @Test
    public void lambdaTest (){

        final Person p1=new Person();
        //p1.setAge(60);

        //works only on 1.8
        doPhilosofize("1st RULE: You do not talk about Lambda expressions.", () -> p1.getAge() > 50);

        p1.setAge(30);
        doPhilosofize("2nd RULE: You DO NOT talk about Lambda expressions.", new MyFunctionalInterface() {
            @Override
            public boolean apply() {
                return p1.getAge() <= 30;
            }
        });
    }

}
