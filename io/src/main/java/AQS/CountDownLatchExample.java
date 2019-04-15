package AQS;

import sun.rmi.runtime.Log;

import java.util.concurrent.*;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class CountDownLatchExample {

    public static int numThread = 200;
    public static Logger log = Logger.getLogger("CountDown");
    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();
        Semaphore s = new Semaphore(100);
        final CountDownLatch count = new CountDownLatch(100);

        for (int i = 0; i < numThread; i++){
            final int threadNum = i;

            exec.execute(()-> {

                try {
                    s.acquire();
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    s.release();
                    count.countDown();
                }
            });
        }

        try {
            count.await(10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            exec.shutdown();
        }
        log.info("finish");
    }

    public static void test(int threadNum) throws InterruptedException {
        sleep(1000);

        log.info(""+threadNum);
    }
}
