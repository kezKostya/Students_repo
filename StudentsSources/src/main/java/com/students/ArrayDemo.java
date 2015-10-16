package com.students;

/**
 * Created by kkolesnichenko on 10/16/2015.
 */
public class ArrayDemo {

    public static void main(String[] args) {
        char[] copyFrom = { 'd', 'e', 'c', 'a', 'f', 'f', 'e',
                'i', 'n', 'a', 't', 'e', 'd' };
        char[] copyTo = new char[7];

        System.arraycopy(copyFrom, 2, copyTo, 0, 7);
        System.out.println(new String(copyTo));
        copyTo = java.util.Arrays.copyOfRange(copyFrom, 2, 9);
        System.out.println(new String(copyTo));

        int[] anArray = new int[10];
        for(int i=0;i<anArray.length;i++){
            anArray[i]=i;
        }
        for(int i: anArray){
            System.out.print(i);
        }
        System.out.println();
        int idx=0;
        while (idx<anArray.length){
            anArray[idx]= anArray[idx]*2;
            idx++;
        }
        System.out.println("Out array:"+anArray);
        System.out.println("Out array with to string:"+java.util.Arrays.toString(anArray));
        idx=0;
        do{
            anArray[idx]= anArray[idx]/2;
            System.out.println( anArray[idx]);
            idx++;
        }while (idx<anArray.length);
        System.out.println("Current indx value:"+idx);
        //repeat once again cycles:
        while (idx<anArray.length){
            System.out.println("Executed while cycle");
        }

        do{
            System.out.println("Executed do-while cycle");
        }while (idx<anArray.length);
    }

}
