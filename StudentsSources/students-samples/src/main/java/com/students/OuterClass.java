package com.students;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by kkolesnichenko on 10/16/2015.
 */
public class OuterClass {

    private String outerClassString="outerClassString";

    private static String outerStaticClassString="outerStaticClassString";

    public static class StaticNestedClass {

        //could be get from instances
        private String innerClassString="innerClassString";

        private static String innerClassStaticString="innerClassStaticString";

        public void doSomething(){
            System.out.print(outerStaticClassString);
        }

        public static void doSomethingStatic(){
            System.out.print(outerStaticClassString);

            //compile time error
            //System.out.print(innerClassString);
        }

        public void doSomethingWithNotStatic(){
            //compile time error
            //System.out.print(outerClassString);

            System.out.print(innerClassString);
        }

    }

    public class InnerClass {

        private String innerClassString="innerClassString";

        //compile error
        //private static String innerClassStaticString="innerClassStaticString";

        public  void printOuterStaticString(){
            System.out.println(outerStaticClassString);
        }

        public  void printInnerStaticString(){
            System.out.println(StaticNestedClass.innerClassStaticString);
        }

        public  void doSomething(){
            System.out.print(outerClassString);
        }

        //public static void doSomethingStatic(){} //compile time error

    }

    public InnerClass instatiateInnerClass(){
        return new InnerClass();
    }

    public void doSomething(){


        Collection myCopyArrayList=new ArrayList();
        String methodString="methodString";
        //methodString=""; //after this wouldn't been effectively final
        Collection myAnonymousArrayList=new ArrayList(){


            private static final String anonymousClassString="anonymousClassString"; //could not be static without final
            //private Long=new Long();
            //compile error
            //private static String innerClassStaticString="innerClassStaticString";

            //specific implementation
            public boolean add(Object obj){
                System.out.println(outerClassString);
                System.out.println(outerStaticClassString);
                System.out.println(anonymousClassString);
                //methodString=""; //compile error at 1.8
                System.out.println(methodString); //compile time error at 1.7 and erarlier;
                System.out.println(StaticNestedClass.innerClassStaticString);
                myCopyArrayList.add(obj);
                return super.add(obj);
            }

            //public static void doSomething(){} //compile time error
        };
        myAnonymousArrayList.add("something");
        System.out.println(methodString);
        InnerClass innerClass=new InnerClass();
        System.out.println(innerClass.innerClassString);



        class LocalClass{

            public void doSomething(){
                System.out.println(methodString);
            }
        };

        LocalClass myLocalClass=new LocalClass();
        myLocalClass.doSomething();
        //compile time error, since myAnonymousArrayList is subtype of Collection
        //System.out.println(myAnonymousArrayList.anonymusClassString);
    }


}
