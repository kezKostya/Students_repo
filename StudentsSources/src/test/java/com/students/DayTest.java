package com.students;

import org.junit.Test;

/**
 * Created by kkolesnichenko on 10/16/2015.
 */
public class DayTest {

    @Test
    public void testName(){
        for(Day day:Day.values()){
            System.out.println(day.ordinal()+" "+day.name()+" "+ day+ " " +day.isWorking());    	day.setIsWorking(false);//no more working days!
        }
        System.out.println();
        for(Day day:Day.values()){
            System.out.println(day.ordinal()+" "+day.name()+" "+ day+ " " +day.isWorking());
        }
        org.junit.Assert.assertTrue(Day.valueOf("SUNDAY")==Day.SUNDAY);	org.junit.Assert.assertTrue(Day.FRIDAY instanceof Enum);
    }

}
