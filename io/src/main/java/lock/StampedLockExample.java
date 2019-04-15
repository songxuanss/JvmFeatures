package lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;
import java.util.logging.Logger;

/**
 * 获取票据
 */
public class StampedLockExample {

    public static StampedLock lock = new StampedLock();

    public static List<String> arrayList = new ArrayList<>();
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

    private static void update (int i) {
        long stamp = lock.writeLock();
        try {
            arrayList.add(i + "");
        } finally{
            lock.unlock(stamp);
        }
    }


}
