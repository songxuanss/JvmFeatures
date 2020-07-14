package lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

/**
 * �����������߳���һ�Σ�����������1
 *
 * Sychronize:
 * 1. JVM ʵ��
 * 2. �Ż����ReentrantLock����
 * 3. ֻ���Ƿǹ�ƽ��
 * 4. ֻ���ǰ�ȫ���̻߳���
 * ReentrantLock:
 * 1. JDK ʵ��
 * 2. ���ܱȽϸߣ����ϵ�Synchronize���ܸ�
 * 3. ����ָ����ƽ�������Ƿǹ�ƽ��
 * 4. ���������� condition, ���Է��黽����Ҫ���ѵ��߳�
 * 5. �ṩ�жϵȴ����Ļ���
 * 6. ʵ���ǻ�����������ͨ������CAS�Ĳ�������ϵͳ�����ں�̬������״̬
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
