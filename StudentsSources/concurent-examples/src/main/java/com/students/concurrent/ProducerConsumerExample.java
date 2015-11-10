package com.students.concurrent;

import java.util.Random;

/**
 * Created by kkolesnichenko on 11/10/2015.
 */
public class ProducerConsumerExample {
    static class Dropbox {
        private int number;
        private boolean empty = true;
        private boolean evenNumber = false;


        public synchronized int take(final boolean even) {
            while (empty || evenNumber != even) {
                try {
                    System.out.format("%s is waiting ... %n", even ? "Even" : "Odd");
                    wait();
                } catch (InterruptedException e) { }
            }
            System.out.format("%s took %d.%n", even ? "Even" : "Odd", number);
            empty = true;
            //notifyAll();
            notify();
            return number;
        }

        public synchronized void put(int number) {
            while (!empty) {
                try {
                    System.out.println("Producer is waiting ...");
                    wait();
                } catch (InterruptedException e) { }
            }
            this.number = number;
            evenNumber = number % 2 == 0;
            System.out.format("Producer put %d.%n", number);
            empty = false;
            //notifyAll();
            notify();
        }
    }


    static class Consumer implements Runnable {

        private final Dropbox dropbox;
        private final boolean even;

        public Consumer(boolean even, Dropbox dropbox) {
            this.even = even;
            this.dropbox = dropbox;
        }

        public void run() {
            Random random = new Random();
            while (true) {
                dropbox.take(even);
                try {
                    Thread.sleep(random.nextInt(100));
                } catch (InterruptedException e) { }
            }
        }
    }

    static class Producer implements Runnable {

        private Dropbox dropbox;

        public Producer(Dropbox dropbox) {
            this.dropbox = dropbox;
        }

        public void run() {
            Random random = new Random();
            while (true) {
                int number = random.nextInt(10);
                try {
                    Thread.sleep(random.nextInt(100));
                    dropbox.put(number);
                } catch (InterruptedException e) { }
            }
        }
    }

    private static boolean Even = true;
    private static boolean Odd = false;

    public static void main(String[] args) {
        Dropbox dropbox = new Dropbox();
        (new Thread(new Consumer(Even, dropbox))).start();
        (new Thread(new Consumer(Odd, dropbox))).start();
        (new Thread(new Producer(dropbox))).start();
    }
}
