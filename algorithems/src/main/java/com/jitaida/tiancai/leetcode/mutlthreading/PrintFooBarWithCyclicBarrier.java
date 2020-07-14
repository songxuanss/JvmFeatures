package com.jitaida.tiancai.leetcode.mutlthreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class PrintFooBarWithCyclicBarrier {
    int n;

    CyclicBarrier barrier = new CyclicBarrier(2);
    CountDownLatch startLatch = new CountDownLatch(1);
    PrintFooBarWithCyclicBarrier(int n) {
        this.n = n;
    }

    public Runnable printfoo = () -> {
        try {
            for (int i = 0; i < n; i++) {
                System.out.print("foo");
                startLatch.countDown();
                barrier.await();
            }
        } catch (Exception e) {
        }
    };

    public Runnable printbar = () -> {
        try {
            for (int i = 0; i < n; i++) {
                startLatch.await();
                System.out.println("bar");
                barrier.await();
                startLatch = new CountDownLatch(1);
            }
        } catch (Exception e) {

        }
    };


    public static void main(String[] args) {
        PrintFooBarWithSemaphore pfb = new PrintFooBarWithSemaphore(5);

        Thread t1 = new Thread(pfb.printfoo);
        Thread t2 = new Thread(pfb.printbar);

        t1.start();
        t2.start();
    }
}
