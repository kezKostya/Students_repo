package com.students.services.common;

import java.util.Comparator;

/**
 * Created by kkolesnichenko on 11/13/2015.
 */
public enum CompareOperator implements ComparePredicate{


    GT {
        @Override
        public boolean test(Comparable t1, Comparable t2) {
            return t1.compareTo(t2)>0;
        }
    },
    LT {
        @Override
        public boolean test(Comparable t1, Comparable t2) {
            return t1.compareTo(t2)<0;
        }
    },
    LTE {
        @Override
        public boolean test(Comparable t1, Comparable t2) {
            return t1.compareTo(t2)<=0;
        }
    },
    GTE {
        @Override
        public boolean test(Comparable t1, Comparable t2) {
            return t1.compareTo(t2)>=0;
        }
    },
    EQUAL {
        @Override
        public boolean test(Comparable t1, Comparable t2) {
            return t1.compareTo(t2)==0;
        }
    };

    /*
        GT(">"),
        LT("<"),
        LTE("<="),
        GTE(">="),
        EQUAL("=");


    private String operator;

    private CompareOperator(String operator){
        this.operator=operator;
    }

    public String getOperator() {
        return operator;
    }
    */
}
