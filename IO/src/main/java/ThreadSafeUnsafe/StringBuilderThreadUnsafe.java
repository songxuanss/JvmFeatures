package ThreadSafeUnsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

/**
 * Thread Not Safe
 */
public class StringBuilderThreadUnsafe {
    public static int totalThread = 5000000;
    public static int totalClient = 2;

    public static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args){
        Logger logger = Logger.getLogger("st");
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(totalClient);

        final CountDownLatch countDownLatch = new CountDownLatch(totalClient);
        for (int i = 0; i < totalThread ; i++){
            executorService.execute(() -> {
                try{
                    semaphore.acquire();
                    update();
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

        logger.info("size of the string "+ stringBuilder.length());

    }

    private static void update () {stringBuilder.append("i");}
}
