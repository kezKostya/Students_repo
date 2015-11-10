package com.students.concurrent;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import com.students.concurrent.HellowWorldThread.ResultCallback;

/**
 * Created by kkolesnichenko on 11/9/2015.
 * All tests from this suite are unstable(except testVolatileSynchronized)
 */
public class InterruptableThreadTest {

    public static class ExecutionCounter {

        private Long value = 0l;

        public Long increment() {
            return value++;
        }

        public Long decrement() {
            return value--;
        }

        public Long getValue() {
            return value;
        }

    }


    public static class CounterResultCallback implements ResultCallback<Long, String> {

        private ExecutionCounter counter;
        private boolean increment;
        private Long innerValue;




        public CounterResultCallback(ExecutionCounter counter, boolean increment) {
            this.counter = counter;
            this.increment = increment;
            this.innerValue = counter.getValue();
        }

        @Override
        public Long apply(String o) {
            if (isIncrement()) {
                innerValue++;
                return counter.increment();
            } else {
                innerValue--;
                return counter.decrement();
            }
        }

        protected boolean isIncrement(){
            return increment;
        }

        public Long getInnerValue() {
            return innerValue;
        }

        public Long getValue() {
            return counter.getValue();
        }


        public ExecutionCounter getCounter(){
            return counter;
        }
    }

    private static class SynchronizedCounterResultCallback extends CounterResultCallback {

        public SynchronizedCounterResultCallback(ExecutionCounter counter, boolean increment) {
            super(counter, increment);
        }

        @Override
        public synchronized Long apply(String o) {
            return super.apply(o);
        }
    }


    private static class VolatileExecutionCounter extends ExecutionCounter{

        private volatile long value = 0l;

        public Long increment() {
            return value++;
        }

        public Long decrement() {
            return value--;
        }

        public Long getValue() {
            return value;
        }
    }

    private static class VolatileSynchronizedCounterResultCallback extends CounterResultCallback {

        private volatile long volatileInnerValue;

        private static final Object monitor=new Object();

        public VolatileSynchronizedCounterResultCallback(ExecutionCounter counter, boolean increment) {
            super(counter, increment);
        }

        @Override
        public  Long apply(String o) {
            synchronized(monitor) {
                Long val = super.apply(o);
                if (isIncrement()) {
                    volatileInnerValue++;
                } else {
                    volatileInnerValue--;
                }
                return val;
            }
        }

        public Long getValue() {
            synchronized(monitor) {
                return super.getValue();
            }
        }

        public Long getInnerValue() {
            return volatileInnerValue;
        }
    }

    public static void executeConcurrentThreads(ResultCallback... callbacks) throws InterruptedException {
        int i=callbacks.length+4;
        Collection<HellowWorldThread> allThreads=new ArrayList<>();
        for(ResultCallback callback:callbacks){
            allThreads.addAll(initialaizeThreads(i--, callback));
        }
        for (Thread thread : allThreads) {
            thread.start();
        }
        Thread.currentThread().sleep(300);
        for (Thread thread : allThreads) {
            thread.interrupt();
        }

    }

    public static Collection<HellowWorldThread> initialaizeThreads(int count, ResultCallback callback) {
        Assert.assertTrue(count > 0);
        ArrayList<HellowWorldThread> threads = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            threads.add(new InterruptableThread("Thread #" + i, callback));
        }
        return threads;
    }

    @Test
    public void testUnsynchronized() throws InterruptedException {
        ExecutionCounter counter = new ExecutionCounter();
        CounterResultCallback incrementCallback = new CounterResultCallback(counter, true);
        executeConcurrentThreads(incrementCallback);
        System.out.println(incrementCallback.getValue());
        System.out.println(incrementCallback.getInnerValue());
        //we incremented 2 values simultanuasly, but they are different
        Assert.assertFalse(incrementCallback.getValue().equals(incrementCallback.getInnerValue()));
    }

    @Test
    public void testSynchronized() throws InterruptedException {
        ExecutionCounter counter = new ExecutionCounter();
        CounterResultCallback incrementCallback = new SynchronizedCounterResultCallback(counter, true);
        executeConcurrentThreads(incrementCallback);
        //synchronized resolves problem from previous test
        Assert.assertTrue(incrementCallback.getValue().equals(incrementCallback.getInnerValue()));


        counter = new ExecutionCounter();
        incrementCallback = new SynchronizedCounterResultCallback(counter, true);
        CounterResultCallback decrementCallback = new SynchronizedCounterResultCallback(counter, false);
        executeConcurrentThreads(incrementCallback, decrementCallback);

        Long decrementValue=decrementCallback.getInnerValue();
        Long incrementValue=incrementCallback.getInnerValue();

        System.out.println(incrementCallback.getValue());
        System.out.println(decrementValue);
        System.out.println(incrementValue);
        Assert.assertTrue(incrementCallback.getValue().equals(decrementCallback.getValue()));
        //but we still have problems if we will try increment and decrement them
        Assert.assertFalse(incrementCallback.getValue().equals(incrementValue + decrementValue));
    }



    @Test
    public void testVolatileSynchronized() throws InterruptedException {
        ExecutionCounter counter = new VolatileExecutionCounter();
        CounterResultCallback incrementCallback = new VolatileSynchronizedCounterResultCallback(counter, true);
        executeConcurrentThreads(incrementCallback);
        Assert.assertTrue(incrementCallback.getValue().equals(incrementCallback.getInnerValue()));


        counter = new VolatileExecutionCounter();
        incrementCallback = new VolatileSynchronizedCounterResultCallback(counter, true);
        CounterResultCallback decrementCallback = new VolatileSynchronizedCounterResultCallback(counter, false);
        executeConcurrentThreads(incrementCallback,decrementCallback);

        Long decrementValue=decrementCallback.getInnerValue();
        Long incrementValue=incrementCallback.getInnerValue();
        Long value=incrementCallback.getValue();

        System.out.println(value);
        System.out.println(decrementValue);
        System.out.println(incrementValue);
        Assert.assertTrue("incrementCallback value="+value+" decrementCallback value="+decrementCallback.getValue(),value.equals(decrementCallback.getValue()));
        //and finally volatile resolve our problems from previous test
        Assert.assertTrue(value.equals(incrementValue + decrementValue));
    }




}
