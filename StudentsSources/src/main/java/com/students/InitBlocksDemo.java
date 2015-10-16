package com.students;

/**
 * Created by kkolesnichenko on 10/16/2015.
 */
public class InitBlocksDemo {

    private String name =doStaticStringInitialization2();

    public  InitBlocksDemo(String name) {
        this.name=name;
        System.out.println("In 1 argument constructor, name = " + this.name);
    }

    public InitBlocksDemo() {
        this("empty") ;
        System.out.println("In no argument constructor, name = " + this.name);

    }

    /* First static initialization block */
    static {
        System.out.println("In first static init block ");
    }

    /* First instance initialization block  */
    {
        System.out.println("In first instance init block, name = " + this.name);
    }

    /* Second instance initialization block */
    {
        System.out.println("In second instance init block, name = " + this.name);
    }

    private static final String STATIC_STRING="static srting";

    public static String doStaticStringInitialization2(){
        System.out.println("In second static method");
        return STATIC_STRING;
    }

    public static String doStaticStringInitialization1(){
        System.out.println("In first static method");
        return STATIC_STRING;
    }

    public static class StaticNestedClass {
        static {
            System.out.println("In nested static init block ");
        }

        {
            System.out.println("In nested non-static block ");
        }
    }


    private static final String secondName=doStaticStringInitialization1();

    /* Second static initialization block  */
    static {
        System.out.println("In second static int block ");
    }



    public static void main(String args[]) {
        System.out.println("Main starts");
        new InitBlocksDemo();
        new InitBlocksDemo("test");
        new InitBlocksDemo.StaticNestedClass();
    }

}
