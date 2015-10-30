package com.students;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kkolesnichenko on 10/14/2015.
 */
public class HashCodeCollisionsTest {

    private static class MyClass{
        private int value;


        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public int hashCode(){
            return value;
        }

        @Override
        public boolean equals(Object oth){
            if(oth ==null) return false;
            if(oth instanceof MyClass){
                return value==((MyClass) oth).getValue();
            }
            else return false;
        }

        @Override
        public String toString(){
            return "MyClass instance with value="+value;
        }
    }



    @Test
    public void hashCollisionTest(){
        HashMap<MyClass,MyClass> map=new HashMap<MyClass,MyClass>();
        MyClass myClass1=new MyClass();
        myClass1.setValue(1);

        MyClass myClass2=new MyClass();
        myClass2.setValue(2);

        map.put(myClass1,myClass1);
        map.put(myClass2,myClass2);

        Assert.assertTrue(map.containsKey(myClass1));
        Assert.assertTrue(map.containsKey(myClass2));
        myClass2.setValue(3);
        Assert.assertFalse(map.containsKey(myClass2));//hash code collision

        //but it is still possible found our broken key using keySet or entrySet
        boolean found=false;
        for(MyClass myClass:map.keySet()){
            if(myClass.equals(myClass2)){
                found=true;
            }
        }
        if(!found){
            Assert.fail();
        }

         found=false;
        for(Map.Entry<MyClass, MyClass> myClass:map.entrySet()){
            if(myClass.getKey().equals(myClass2)){
                found=true;
            }
        }
        if(!found){
            Assert.fail();
        }

        //but event resize of map wouldn't help(see load factor and map-size constructors)
        for(int i=4;i<64;i++){
            putToMap(i,map);
        }
        Assert.assertFalse(map.containsKey(myClass2));
    }

    private void putToMap(int value, Map<MyClass,MyClass> map){
        MyClass myClass=new MyClass();
        myClass.setValue(value);
        map.put(myClass,myClass);
    }


}
