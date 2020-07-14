package com.jitaida.tiancai.leetcode.mutlthreading;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class ZeroEvenOddSemaphore {
        private int n;
        private int cur;
        static int odd = 1;
        static int even = 2;
        private int zero = 0;
        Semaphore zeroSema = new Semaphore(1);
        Semaphore oddSema = new Semaphore(1);
        Semaphore evenSema = new Semaphore(0);
        boolean shouldOdd;


        public ZeroEvenOddSemaphore(int n) {
            this.n = n;
            this.cur = 0;
            this.shouldOdd = true;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            while(true){
                zeroSema.acquire();
                if (this.cur == this.n) {
                    oddSema.release(2);
                    evenSema.release(2);
                    return;
                }
                printNumber.accept(zero);
                if (shouldOdd){
                    this.oddSema.release();
                } else {
                    this.evenSema.release();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            while(true) {
                evenSema.acquire(2);
                if (this.cur == this.n) {
                    zeroSema.release();
                    oddSema.release(2);
                    return;
                }
                printNumber.accept(even);
                shouldOdd = true;
                even += 2;
                this.cur += 1;
                zeroSema.release();
                oddSema.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            while(true) {
                oddSema.acquire(2);
                if (this.cur == this.n) {
                    zeroSema.release();
                    evenSema.release(2);
                    return;
                }
                printNumber.accept(odd);
                shouldOdd = false;
                odd += 2;
                this.cur += 1;
                zeroSema.release();
                evenSema.release();
            }
        }

        public static void main(String[] args){
            ZeroEvenOddSemaphore working = new ZeroEvenOddSemaphore(5);

            IntConsumer intConsumer = e -> System.out.print(e);

            Thread threadzero = new Thread() {
                @Override
                public void run() {
                    try {
                        working.zero(intConsumer);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            Thread threadOdd = new Thread() {
                @Override
                public void run() {
                    try {
                        working.odd(intConsumer);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            Thread threadEven = new Thread() {
                @Override
                public void run() {
                    try {
                        working.even(intConsumer);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            threadzero.start();
            threadEven.start();
            threadOdd.start();
        }

}
