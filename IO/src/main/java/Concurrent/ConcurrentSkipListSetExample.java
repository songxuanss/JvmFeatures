package Concurrent;

import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * 原子操作是线程安全的
 * 批量操作不是线程安全的
 */
public class ConcurrentSkipListSetExample {
    public static ConcurrentSkipListSet<String> arrayList = new ConcurrentSkipListSet<String>();
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
