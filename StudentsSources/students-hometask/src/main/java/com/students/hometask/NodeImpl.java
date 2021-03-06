package com.students.hometask;

import com.students.annotation.NotNull;

/**
 * Created by kkolesnichenko on 10/23/2015.
 */
public class NodeImpl<T> implements Node<T> {

   @NotNull
   private T value;
   private Node<T> next;

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public Node<T> next() {
        return next;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
