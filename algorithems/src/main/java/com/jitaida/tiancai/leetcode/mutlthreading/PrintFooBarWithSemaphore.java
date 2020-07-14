package com.jitaida.tiancai.leetcode.mutlthreading;

import java.util.concurrent.Semaphore;

public class PrintFooBarWithSemaphore {
    Semaphore fooSema = new Semaphore(1);
    Semaphore barSema = new Semaphore(0);
    int n;

    PrintFooBarWithSemaphore(int n) {
        this.n = n;
    }

    public Runnable printfoo = () -> {
        try {
            for (int i = 0; i < n; i++) {
                fooSema.acquire();
                System.out.print("foo");
                barSema.release();
            }
        } catch (Exception e) {
        }
    };

    public Runnable printbar = () -> {
        try {
            for (int i = 0; i < n; i++) {
                barSema.acquire();
                System.out.println("bar");
                fooSema.release();
            }
        } catch (Exception e) {

        }
    };

    public static void main(String[] args) {
        PrintFooBarWithSemaphore pfb = new PrintFooBarWithSemaphore(10);

        Thread t1 = new Thread(pfb.printfoo);
        Thread t2 = new Thread(pfb.printbar);

        t1.start();
        t2.start();
    }
}
