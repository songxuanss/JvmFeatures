package AQS;

import java.util.concurrent.*;
import java.util.logging.Logger;

public class FutureTaskExample {
        public static Logger logger = Logger.getLogger("Future");

        static class MyCallable implements Callable<String> {

            @Override
            public String call() throws Exception {
                logger.info("set out");
                Thread.sleep(5000);
                return "Done";
            }
        }

        static FutureTask<String> futureTask = new FutureTask<>(new MyCallable());

        public static void main(String[] ag){
            futureTask.run();
            try {
                String out = futureTask.get();
                logger.info("get out " + out);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
