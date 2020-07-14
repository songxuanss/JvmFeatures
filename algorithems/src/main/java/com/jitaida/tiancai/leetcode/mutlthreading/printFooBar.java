package com.jitaida.tiancai.leetcode.mutlthreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class printFooBar {

    private int n;
    ReentrantLock lock = new ReentrantLock();
    Condition fooCondition;
    Condition barCondition;
    volatile boolean shouldFoo = true;

    Runnable printFoo = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < n; i++) {
                lock.lock();
                if (!shouldFoo) {
                    try {
                        fooCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                System.out.print("foo");
                shouldFoo = false;
                barCondition.signal();
                lock.unlock();
            }

        }
    };

    Runnable printBar = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < n; i++) {
                lock.lock();
                if(shouldFoo) {
                    try {
                        barCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                System.out.println("bar");
                shouldFoo = true;
                fooCondition.signal();
                lock.unlock();
            }
        }
    };

    public printFooBar(int n) {
        fooCondition = lock.newCondition();
        barCondition = lock.newCondition();
        this.n = n;
    }

    public static void main(String[] args) {
        printFooBar pfb = new printFooBar(20);
        Thread tbar = new Thread(pfb.printBar);
        Thread tfoo = new Thread(pfb.printFoo);
        tbar.start();
        tfoo.start();
    }
}
