package com.students.services.common;

/**
 * Created by kkolesnichenko on 11/14/2015.
 */
public interface ComparePredicate<T extends Comparable> {

    boolean test(T t1, T t2);

    String sqlOperator();

}
