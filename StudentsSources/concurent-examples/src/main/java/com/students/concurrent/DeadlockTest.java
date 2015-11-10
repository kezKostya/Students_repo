package com.students.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kkolesnichenko on 11/10/2015.
 */
public class DeadlockTest {

    private static class DeadlockResultCallback implements HellowWorldThread.ResultCallback {

        private DeadlockResultCallback delegate;



        @Override
        public synchronized Object apply(Object o) {
            while(true) {
                System.out.println("Executes apply " + this.toString());
                return delegate.applySynchronized();
            }
        }

        public synchronized Object applySynchronized(){
            System.out.println("Executes applySynchronized in "+this.toString());
            return null;
        }

        public DeadlockResultCallback getDelegate() {
            return delegate;
        }

        public void setDelegate(DeadlockResultCallback delegate) {
            this.delegate = delegate;
        }
    }

    private static class ReentantLocksResultCallback implements HellowWorldThread.ResultCallback {
        private ReentantLocksResultCallback delegate;
        private final Lock lock = new ReentrantLock();

        public boolean acquireLock() {
            Boolean myLock = false;
            Boolean delegateLock = false;
            try {
                myLock = lock.tryLock();
                delegateLock=delegate.getLock().tryLock();
            } finally {
                if (! (myLock && delegateLock)) {
                    if (myLock) {
                        lock.unlock();
                    }
                    if (delegateLock) {
                        delegate.getLock().unlock();
                    }
                }
            }
            return myLock && delegateLock;
        }

        @Override
        public  Object apply(Object o) {
            while(true) {
                try {

                    if (acquireLock()) {
                        System.out.println("Executes apply " + this.toString());
                        return delegate.applySynchronized();
                    }
                }finally{
                    lock.unlock();
                    delegate.getLock().unlock();
                }

            }
        }

        public  Object applySynchronized(){
            System.out.println("Executes applySynchronized in "+this.toString());
            return null;
        }

        public ReentantLocksResultCallback getDelegate() {
            return delegate;
        }

        public void setDelegate(ReentantLocksResultCallback delegate) {
            this.delegate = delegate;
        }

        public Lock getLock() {
            return lock;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadlockResultCallback callback1 = new DeadlockResultCallback();
        DeadlockResultCallback callback2 = new DeadlockResultCallback();

        //ReentantLocksResultCallback callback1 = new ReentantLocksResultCallback();
        //ReentantLocksResultCallback callback2 = new ReentantLocksResultCallback();
        callback1.setDelegate(callback2);
        callback2.setDelegate(callback1);
        InterruptableThread thread1=new InterruptableThread("Thread 1" , callback1);

        InterruptableThread thread2=new InterruptableThread("Thread 2" , callback2);

        thread1.start();
        thread2.start();

        Thread.currentThread().sleep(400);
        System.out.println("Thread 1 state=" + thread1.getState());
        System.out.println("Thread 2 state="+thread2.getState());
        thread1.interrupt();//even interrupt wouldn't help
        thread2.interrupt();
    }
}
