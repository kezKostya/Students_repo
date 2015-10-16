package com.students;

/**
 * Created by kkolesnichenko on 10/16/2015.
 */
public enum Day {
    MONDAY(true),
    TUESDAY(true),
    WEDNESDAY(true),
    THURSDAY(true),
    FRIDAY(true),
    SATURDAY(false),
    SUNDAY(false);
    private boolean isWorking;

    Day(boolean isWorking) {
        //super("",1);
        this.isWorking = isWorking;
    }

    Day(String name, int ordinal){
    }

    public boolean isWorking() {
        return isWorking;
    }
    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

}
