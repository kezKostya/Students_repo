package com.students.hometask;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkolesnichenko on 10/23/2015.
 */
public class CycledTest {


    /**
     * Is checks that is cycled for O(n) time)
     * @param node
     * @param <T>
     * @return
     */
    public static  <T> boolean isCycled(Node<T> node){
        Node one=node;
        Node two =node.next();
        boolean isCycled=(one==two);
        while(two.next()!=null && two.next().next()!=null && !isCycled){
            one = one.next();
            two=two.next().next();
            isCycled=(one==two);
        }
        return isCycled;
    }

    public static  <T> boolean isCycledAdditionalMemory(Node<T> node){
        List list=new ArrayList<>();
        Node one=node;
        list.add(one);
        while(one.next()!=null){
            one = one.next();
            if (list.contains(one)) return true;
            list.add(one);
        }
        return false;
    }

    public <T> boolean isCycled2(Node<T> node){
        Node one=node;
        Node two =node.next();
        boolean isCycled=(one==two);
        int i=0;
        while(one.next()!=null && !isCycled){
            int j=0;
            while(two.next()!=null && !isCycled &&j<i){
                two=two.next();
                isCycled=(one==two);
                j++;
            }
            i++;
            one = one.next();
        }
        return isCycled;
    };

    public static Node initializedList(){
        NodeImpl nodeFirst = new NodeImpl();
        NodeImpl nodeTwo = new NodeImpl();
        NodeImpl nodeThree = new NodeImpl();
        NodeImpl nodeFour = new NodeImpl();
        NodeImpl nodeFive = new NodeImpl();


        nodeFirst.setValue(1);
        nodeTwo.setValue(2);
        nodeThree.setValue(3);
        nodeFour.setValue(4);
        nodeFive.setValue(5);


        nodeFirst.setNext(nodeTwo);
        nodeTwo.setNext(nodeThree);
        nodeThree.setNext(nodeFour);
        nodeFour.setNext(nodeFive);
        nodeFive.setNext(nodeTwo);
        return nodeFirst;

    }

    @Test
    public void cycledTest(){
        Node node=  initializedList();
        Assert.assertTrue(isCycled(node));
        ((NodeImpl)node.next().next().next().next()).setNext(null);
        Assert.assertFalse(isCycled(node));
    }

    @Test
    public void cycledTestWithAdditionalMemory(){
        Node node= initializedList();
        Assert.assertTrue(isCycledAdditionalMemory(node));
        ((NodeImpl)node.next().next().next().next()).setNext(null);
        Assert.assertFalse(isCycledAdditionalMemory(node));
    }

    @Test
    public void cycledTestQuadraticTime(){
        Node node= initializedList();
        //Assert.assertTrue(isCycled2(node));
        ((NodeImpl)node.next().next().next().next()).setNext(null);
        Assert.assertFalse(isCycled2(node));
    }


    public static IterableNodeImpl initializedIterableList(){
        IterableNodeImpl nodeFirst = new IterableNodeImpl();
        NodeImpl nodeTwo = new IterableNodeImpl();
        NodeImpl nodeThree = new IterableNodeImpl();
        NodeImpl nodeFour = new IterableNodeImpl();
        NodeImpl nodeFive = new IterableNodeImpl();


        nodeFirst.setValue(1);
        nodeTwo.setValue(2);
        nodeThree.setValue(3);
        nodeFour.setValue(4);
        nodeFive.setValue(5);


        nodeFirst.setNext(nodeTwo);
        nodeTwo.setNext(nodeThree);
        nodeThree.setNext(nodeFour);
        nodeFour.setNext(nodeFive);
        nodeFive.setNext(nodeTwo);
        return nodeFirst;
    }


    public static  <T> boolean isCycledIterable(IterableNodeImpl<T> node){
        if(node==null) return false;

        IterableNodeImpl<T> second = (IterableNodeImpl<T>) node.next();
        int i=0;
        for(IterableNodeImpl<T> nodeCurrent:node){
            int j=0;
            IterableNodeImpl<T> tmpNode= (IterableNodeImpl<T>) nodeCurrent.next();
            if(tmpNode==null) return false;
            for(IterableNodeImpl<T> nodeCurrent2:tmpNode){
                if(nodeCurrent==nodeCurrent2) return true;
                if(j>i) break;
                j++;
            }
            i++;
        }
        return false;
    }

    @Test
    public void iterableListTest(){
        IterableNodeImpl node= initializedIterableList();
        Assert.assertTrue(isCycledIterable(node));
        ((NodeImpl)node.next().next().next().next()).setNext(null);
        Assert.assertFalse(isCycledIterable(node));
    }

}
