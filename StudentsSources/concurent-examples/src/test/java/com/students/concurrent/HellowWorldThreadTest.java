package com.students.concurrent;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by kkolesnichenko on 11/9/2015.
 */
public class HellowWorldThreadTest {


    private Collection<Thread> initialaizeThreads(int count,Collection<String> executedThreads){
        Assert.assertTrue(count>0);
        ArrayList<Thread> threads=new ArrayList<>(count);
        for(int i=0;i<count;i++){
            threads.add(new HellowWorldThread("Thread #"+i, new HellowWorldThread.ResultCallback<Collection<String> ,String>(){

                @Override
                public Collection<String>  apply(String s) {
                    executedThreads.add(s);
                    return executedThreads;
                }
            }));
        }
        return threads;
    }

    @Test
    public void testThreads() throws InterruptedException {
        int threadCount=50;
        ArrayList<String> executedThreads=new ArrayList<>();
        Collection<Thread> threads=initialaizeThreads(threadCount,executedThreads);
        for(Thread thread:threads){
            thread.start();
        }
        for(Thread thread:threads){
            //wait for thread complication
            thread.join();
        }
        ArrayList<String> executedThreads2=new ArrayList<>();
        threads=initialaizeThreads(threadCount,executedThreads2);

        for(Thread thread:threads){
            thread.run();
        }
        Assert.assertTrue(executedThreads2.size()== executedThreads.size());
    }


    @Test
    public void testThreadsWithoutJoin() {
        int threadCount=50;
        ArrayList<String> runnedThreads=new ArrayList<>();
        Collection<Thread> threads=initialaizeThreads(threadCount,runnedThreads);
        for(Thread thread:threads){
            thread.run();
        }

        ArrayList<String> startedThreads=new ArrayList<>();
        threads=initialaizeThreads(threadCount,startedThreads);

        for(Thread thread:threads){
            thread.start();
        }

        //actually this test is unstable, since it is possible that all 50 threads will finish before this line(raise-conditions)
        Assert.assertTrue(startedThreads.size()< runnedThreads.size());
    }
}
