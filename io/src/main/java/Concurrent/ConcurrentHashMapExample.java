package Concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * 线程安全，速度快
 */
public class ConcurrentHashMapExample {

    public static int totalThread = 5000;
    public static int totalClient = 2;

    public static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args){
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

        logger.info("size of the string "+ map.size());

    }

    private static void update (int i) {map.put(i,i);}
}
