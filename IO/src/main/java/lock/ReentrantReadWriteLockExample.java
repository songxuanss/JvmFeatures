package lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

/**
 *
 * 悲观执行，如果有readlock，那么writelock则一直等待
 * 容易造成饥饿
 *
 */
public class ReentrantReadWriteLockExample {
        public static List<String> arrayList = new ArrayList<>();
        public static int totalThread = 5000;
        public static int totalClient = 2;
        public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        public static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        public static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

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
                        read(count);
                        write("test" + count);
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

        private static void read (int i) {
            readLock.lock();
            try {
                arrayList.get(i);
            } finally{
                readLock.unlock();
            }
        }

        private static void write(String value) {
            writeLock.lock();
            try {
                arrayList.add(value);
            }finally {
                writeLock.unlock();
            }
        }


    }

