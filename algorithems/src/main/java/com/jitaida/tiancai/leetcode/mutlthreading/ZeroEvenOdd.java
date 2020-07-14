package com.jitaida.tiancai.leetcode.mutlthreading;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;


/**
 * 假设有这么一个类：
 *
 * class ZeroEvenOdd {
 * ? public ZeroEvenOdd(int n) { ... }?     // 构造函数
 *   public void zero(printNumber) { ... }  // 仅打印出 0
 *   public void even(printNumber) { ... }  // 仅打印出 偶数
 *   public void odd(printNumber) { ... }   // 仅打印出 奇数
 * }
 * 相同的一个?ZeroEvenOdd?类实例将会传递给三个不同的线程：
 *
 * 线程 A 将调用?zero()，它只输出 0 。
 * 线程 B 将调用?even()，它只输出偶数。
 * 线程 C 将调用?odd()，它只输出奇数。
 * 每个线程都有一个?printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列?010203040506... ，其中序列的长度必须为 2n。
 *
 * ?
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出："0102"
 * 说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为
 *
 */
public class ZeroEvenOdd {
    private int n;
    private int cur;
    static int odd = 1;
    static int even = 2;
    private int zero = 0;
    Semaphore zeroSema = new Semaphore(1);
    Semaphore nonZeroSema = new Semaphore(0);
    Condition oddCondition;
    Condition evenCondition;
    boolean shouldOdd;
    ReentrantLock lock;


    public ZeroEvenOdd(int n) {
        this.n = n;
        this.cur = 0;
        this.shouldOdd = true;
        this.lock = new ReentrantLock();
        this.oddCondition = this.lock.newCondition();
        this.evenCondition = this.lock.newCondition();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while(true){
            zeroSema.acquire();
            if (this.cur == this.n) {
                nonZeroSema.release(2);
                return;
            }
//            System.out.println("zero");
            printNumber.accept(zero);
            this.nonZeroSema.release();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while(true) {
            this.lock.lock();
            if (shouldOdd)
                evenCondition.await();
            if (this.cur == this.n) {
                zeroSema.release();
                oddCondition.signal();
                this.lock.unlock();
                return;
            }
            nonZeroSema.acquire();
//            System.out.println("eve");
            printNumber.accept(even);
            shouldOdd = true;
            even += 2;
            this.cur += 1;
            oddCondition.signal();
            zeroSema.release();
            this.lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while(true) {
            this.lock.lock();
            if (!shouldOdd)
                oddCondition.await();
            if (this.cur == this.n) {
                zeroSema.release();
                evenCondition.signal();
                this.lock.unlock();
                return;
            }
            nonZeroSema.acquire();
//            System.out.println("odd");
            printNumber.accept(odd);
            shouldOdd = false;
            odd += 2;
            this.cur += 1;
            zeroSema.release();
            evenCondition.signal();
            this.lock.unlock();
        }
    }

    public static void main(String[] args){
        ZeroEvenOdd  working = new ZeroEvenOdd(5);

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
