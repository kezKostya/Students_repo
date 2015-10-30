package com.students;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Created by kkolesnichenko on 10/14/2015.
 */
public class CollectionTest {

    @Test(expected = ConcurrentModificationException.class)
    public void forEachTest(){
        Collection<Integer> myCollection=new ArrayList<Integer>();
        myCollection.add(1);
        myCollection.add(2);
        myCollection.add(3);
        myCollection.add(4);
        for(Integer i:myCollection){
            if(i==2){
                myCollection.remove(i); //throws ConcurrentModificationException - reason implicit creation of iterator.
            }

        }
    }

    @Test
    public void iteratorTest(){
        Collection<Integer> myCollection=new ArrayList<Integer>();
        myCollection.add(1);
        myCollection.add(2);
        myCollection.add(3);
        myCollection.add(4);
        Iterator<Integer> iterator=myCollection.iterator(); //explicit creation of iterator
        while(iterator.hasNext()){
            Integer i=iterator.next();
            if(i==2){
                iterator.remove();
                //myCollection.remove(i); // this is what actually happens in forEachTest()
            }

        }
    }
}
