package lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

/**
 * 可重入锁，线程锁一次，计数器增加1
 *
 * Sychronize:
 * 1. JVM 实现
 * 2. 优化后和ReentrantLock类似
 * 3. 只能是非公平锁
 * 4. 只能是把全部线程唤醒
 * ReentrantLock:
 * 1. JDK 实现
 * 2. 性能比较高，比老的Synchronize性能高
 * 3. 可以指定公平锁或者是非公平锁
 * 4. 有条件限制 condition, 可以分组唤醒需要唤醒的线程
 * 5. 提供中断等待锁的机制
 * 6. 实现是基于自旋锁，通过类似CAS的操作避免系统进入内核态的阻塞状态
 */
public class ReentrantLockExample {
    public static List<String> arrayList = new ArrayList<>();
    public static int totalThread = 5000;
    public static int totalClient = 2;
    public static Lock reentrantLock = new ReentrantLock();

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
        reentrantLock.lock();
        try {
            arrayList.add(i + "");
        } finally{
            reentrantLock.unlock();
        }
    }


}
