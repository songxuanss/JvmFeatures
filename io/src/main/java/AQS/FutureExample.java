package AQS;

import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * 可以取消任务，查询任务是否取消
 *
 * 监视目标线程调用call的情况，可得到别的线程任务的返回值。
 *
 */
public class FutureExample {
    public static Logger logger = Logger.getLogger("Future");
    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            logger.info("set out");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] ag){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        try {
            String out = future.get();
            logger.info("get out " + out);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
