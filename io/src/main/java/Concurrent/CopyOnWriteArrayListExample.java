package Concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * 原理：
 * 每次添加删除元素，CopyOnWriteArrayList是在内存中开辟一块新的内存空间，把添加或者删除后的array地址指向给原来的变量
 *
 *
 * Disadvantage:
 * 1. Esay to meet young gc or full gc
 * 2. 不能马上获取最新的值
 *
 * Advantage:
 * 1. 读写分离，不需要锁操作，增加性能
 * 2. 保证最终一致性
 * 3. 开辟空间保证不冲突
 */
public class CopyOnWriteArrayListExample {


    public static CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();
    public static int totalThread = 5000;
    public static int totalClient = 2;

    public static void main(String [] args){
        Logger logger = Logger.getLogger("st");
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(totalClient);

        final CountDownLatch countDownLatch = new CountDownLatch(totalClient);
        for (int i = 0; i < totalThread ; i++){
            final int count = i;
            executorService.execute(() -> {
                try{
                    semaphore.acquire();
                    update(count);
                    semaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
            logger.info("x");
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            executorService.shutdown();
        }

        logger.info("size of the string "+ arrayList.size());

    }

    private static void update (int i) {arrayList.add(i+"");}

}
