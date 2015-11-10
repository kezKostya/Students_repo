package com.students.concurrent;

/**
 * Created by kkolesnichenko on 11/9/2015.
 */
public class HellowWorldThread extends Thread {

    private String name;
    private ResultCallback callback;

    protected ResultCallback getCallback(){
        return callback;
    }

    public HellowWorldThread(String name,ResultCallback callback){
        this.name=name;
        this.callback=callback;
    }

    public void run() {
        String result=name+": Hello from a thread!";
        callback.apply(result);
       // System.out.println(result);
    }

    public static interface ResultCallback<T,R>{
        T apply(R r);
    }
}
