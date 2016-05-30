package com.students;


import org.junit.Ignore;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by kkolesnichenko on 10/12/2015.
 */
public class SeachingTest {



   /*   public static int binarySearch(int[] a, int chislo) {
        int begin = 0;
        int end = a.length ;
        while (begin <= end) {
            int mid = begin + (end - begin) / 2;
            if (chislo < a[mid])
                end = mid;
            else {
                if (chislo > a[mid])
                    begin = mid;
                else
                    return mid;
            }
        }
        return 0;
    }
*/

    public static int binarySearch(int[] a, int key) {
                 int low = 0;
                 int high = a.length - 1;

                 while (low <= high) {
                         int mid = (low + high) >>> 1;
                         int midVal = a[mid];

                         if (midVal < key)
                             low = mid + 1;
                         else if (midVal > key)
                                high = mid - 1;
                         else
                             return mid; // key found
                     }
                return -(low + 1);  // key not found.
    }


    public static int interpolationSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;

        //check on emptiness
        if(low <= high ) {
            //check corner case
            if (a[low] == key)
                return low;
            else if (a[high] == key)
                return high;

            while (a[low] < key && a[high] > key) {
                int mid = low + ((key - a[low]) * (high - low)) / (a[high] - a[low]);
                int midVal = a[mid];

                if (midVal < key)
                    low = mid + 1;
                else if (midVal > key)
                    high = mid - 1;
                else
                    return mid; // key found
            }
        }
        return -(low + 1);  // key not found.
    }

    @Test
    public void testExists(){
        int[] mas = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
        int key=12;
        int idx=binarySearch(mas, key);
        Assert.assertTrue(mas[idx]==key);
    }

    @Test
    public void testExistsAll(){
        int[] mas = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
        for(int key:mas){
            testExists(mas,key);
        }
    }

    private void testExists( int[] mas,int key){
        int idx=binarySearch(mas, key);
        if(idx==-1){
            org.junit.Assert.fail("Not found index for key="+key);
        }
        Assert.assertTrue(mas[idx]==key);
    }



    @Test
    public void testBinarySearchForEmptyArray(){
        int[] mas = new int[]{};
        int idx=  binarySearch(mas,1); // IOB exception
        Assert.assertTrue(idx == -1);

    }

    @Test
    public void testNumberNotPartOfArray(){
        int[] mas = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
        int idx=binarySearch(mas, 13);
        Assert.assertTrue(idx<0);
    }


    ///

    @Test
    public void testExists_interpolationSearch(){
        int[] mas = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
        int key=12;
        int idx=interpolationSearch(mas, key);
        Assert.assertTrue(mas[idx]==key);
    }

    @Test
    public void testExistsAll_interpolationSearch(){
        int[] mas = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
        for(int key:mas){
            testExists_interpolationSearch(mas, key);
        }
    }

    private void testExists_interpolationSearch( int[] mas,int key){
        int idx=interpolationSearch(mas, key);
        if(idx==-1){
            org.junit.Assert.fail("Not found index for key="+key);
        }
        Assert.assertTrue(mas[idx]==key);
    }



    @Test
    public void testBinarySearchForEmptyArray_interpolationSearch(){
        int[] mas = new int[]{};
        int idx=  interpolationSearch(mas, 1); // IOB exception
        Assert.assertTrue(idx == -1);

    }

    @Test
    public void testNumberNotPartOfArray_interpolationSearch(){
        int[] mas = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
        int idx=interpolationSearch(mas, 10);
        Assert.assertTrue(idx<0);
    }
    ///

    //run with -Xmx7g for 64bit machine
    //or with "-Xmx4g -d32" if downloaded jvm supports 32 bit compatibile mode
    @Ignore
    @Test
    public void testBinarySearchForIntegerMax(){
        int[] mas=new int[Integer.MAX_VALUE/2 +100];
        for(int i=0;i<mas.length;i++){
            mas[i]=i;
        }
        int key=  mas.length-5;
        int idx=  binarySearch(mas,key);

        org.junit.Assert.assertTrue(mas[idx]==key);
    }

}
