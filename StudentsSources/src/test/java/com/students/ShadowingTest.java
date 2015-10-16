package com.students;

import org.junit.Test;

/**
 * Created by kkolesnichenko on 10/16/2015.
 */
public class ShadowingTest {

    public static class Animal {
        public static void testClassMethod() {
            System.out.println("The static method in Animal");
        }

        public void testInstanceMethod() {
            System.out.println("The instance method in Animal");
        }
    }

    public static class Cat extends Animal {
        public static void testClassMethod() {
            System.out.println("The static method in Cat");
        }

        public void testInstanceMethod() {
            System.out.println("The instance method in Cat");
        }



    }

    @Test
    public void shadowingTest() {
        Cat myCat = new Cat();
        Animal myAnimal = myCat;
        Animal.testClassMethod();
        myAnimal.testInstanceMethod();
        myCat.testClassMethod();

        Animal myAnimal2 = new Animal();
        myAnimal2.testClassMethod();
    }

}
