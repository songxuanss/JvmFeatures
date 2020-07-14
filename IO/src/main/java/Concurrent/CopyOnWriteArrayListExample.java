package Concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * ԭ��
 * ÿ�����ɾ��Ԫ�أ�CopyOnWriteArrayList�����ڴ��п���һ���µ��ڴ�ռ䣬����ӻ���ɾ�����array��ַָ���ԭ���ı���
 *
 *
 * Disadvantage:
 * 1. Esay to meet young gc or full gc
 * 2. �������ϻ�ȡ���µ�ֵ
 *
 * Advantage:
 * 1. ��д���룬����Ҫ����������������
 * 2. ��֤����һ����
 * 3. ���ٿռ䱣֤����ͻ
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
