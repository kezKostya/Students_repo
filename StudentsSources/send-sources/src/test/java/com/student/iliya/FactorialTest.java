package com.student.iliya;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by kkolesnichenko on 10/30/2015.
 */
public class FactorialTest {

    public static int recursiveFact(int n) {
        int r;

        if (n == 1)
            return 1;
        r = recursiveFact(n - 1) * n;
        return r;
    }


    public static int fact(int n){
        // ????? ??? ????????
        int r=1;
        for(int i=1;i<=n;i++)
            r=r*i;
        return r;
    }

    @Test
    public void compareTest(){
        int idx1 = fact(4);
        int idx2 = recursiveFact(4);
        Assert.assertTrue(idx1 == idx2);
    }

    @Test
    public void  testFactorial() {
        org.junit.Assert.assertTrue(fact(0) == 1);
        org.junit.Assert.assertTrue(recursiveFact(1) == 1);
        org.junit.Assert.assertTrue(fact(40)>0);//fails
    }

}
