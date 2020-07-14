package com.jitaida.tiancai.leetcode.mutlthreading;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;


/**
 * ��������ôһ���ࣺ
 *
 * class ZeroEvenOdd {
 * ? public ZeroEvenOdd(int n) { ... }?     // ���캯��
 *   public void zero(printNumber) { ... }  // ����ӡ�� 0
 *   public void even(printNumber) { ... }  // ����ӡ�� ż��
 *   public void odd(printNumber) { ... }   // ����ӡ�� ����
 * }
 * ��ͬ��һ��?ZeroEvenOdd?��ʵ�����ᴫ�ݸ�������ͬ���̣߳�
 *
 * �߳� A ������?zero()����ֻ��� 0 ��
 * �߳� B ������?even()����ֻ���ż����
 * �߳� C ������?odd()����ֻ���������
 * ÿ���̶߳���һ��?printNumber ���������һ�����������޸ĸ����Ĵ����������������?010203040506... ���������еĳ��ȱ���Ϊ 2n��
 *
 * ?
 *
 * ʾ�� 1��
 *
 * ���룺n = 2
 * �����"0102"
 * ˵���������߳��첽ִ�У�����һ������ zero()����һ���̵߳��� even()�����һ���̵߳���odd()����ȷ�����Ϊ
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
