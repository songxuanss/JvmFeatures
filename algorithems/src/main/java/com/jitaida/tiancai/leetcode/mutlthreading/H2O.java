package com.jitaida.tiancai.leetcode.mutlthreading;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;


public class H2O {
    String str;
    CountDownLatch hydronCount = new CountDownLatch(2);
    CountDownLatch oxygenCount = new CountDownLatch(1);
    CyclicBarrier barrier = new CyclicBarrier(2);

    Runnable hydrogen = new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());

            for (int i = 0 ; i < str.length(); i++){
                if (str.charAt(i) == 'O') continue;
                // releaseHydrogen.run() outputs "H". Do not change or remove this line.
                System.out.println(str.charAt(i));
                hydronCount.countDown();
                if (hydronCount.getCount() == 0){
                    try {
                        System.out.println("Start h");
                        barrier.await();
                        System.out.println("End h");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    oxygenCount = new CountDownLatch(1);
                }
            }
        }
    };

    Runnable oxygen = new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            for (int i = 0 ; i < str.length(); i++){
                if (str.charAt(i) == 'H') continue;
                System.out.println(str.charAt(i));
                oxygenCount.countDown();

                try {
                    System.out.println("Start o");
                    hydronCount.await();
                    barrier.await();
                    System.out.println("End o");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

                hydronCount = new CountDownLatch(2);
            }
        }
    };

   public H2O(String str){
       this.str = str;
   }

   public void run() {
        Thread tH = new Thread(hydrogen);
        Thread tO = new Thread(oxygen);

        tH.start();
        tO.start();
   }

   public static void main(String[] args) throws Exception {
       H2O test=new H2O("OHH");
       test.run();
   }
}
