package com.students.concurrent;

/**
 * Created by kkolesnichenko on 11/9/2015.
 */
public class InterruptableThread extends HellowWorldThread {

    public InterruptableThread(String name, ResultCallback callback) {
        super(name, callback);
    }

    public void run() {
       while (true){
           try {
               super.run();
               Thread.sleep(10);//pause 0.01 seconds
           } catch (InterruptedException e) {
             //  System.out.println("Thread was interrupted!");
             return; //stop excution
           }
       }
    }
}
