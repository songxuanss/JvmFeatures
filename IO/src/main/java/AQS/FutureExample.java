package AQS;

import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * ����ȡ�����񣬲�ѯ�����Ƿ�ȡ��
 *
 * ����Ŀ���̵߳���call��������ɵõ�����߳�����ķ���ֵ��
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
