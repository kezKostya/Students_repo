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

        @Override
        public String sqlOperator() {
            return ">";
        }
    },
    LT {
        @Override
        public boolean test(Comparable t1, Comparable t2) {
            return t1.compareTo(t2)<0;
        }

        @Override
        public String sqlOperator() {
            return "<";
        }
    },
    LTE {
        @Override
        public boolean test(Comparable t1, Comparable t2) {
            return t1.compareTo(t2)<=0;
        }

        @Override
        public String sqlOperator() {
            return "<=";
        }
    },
    GTE {
        @Override
        public boolean test(Comparable t1, Comparable t2) {
            return t1.compareTo(t2)>=0;
        }

        @Override
        public String sqlOperator() {
            return ">=";
        }
    },
    EQUAL {
        @Override
        public boolean test(Comparable t1, Comparable t2) {
            return t1.compareTo(t2)==0;
        }

        @Override
        public String sqlOperator() {
            return "=";
        }
    };


}
