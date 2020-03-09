package AQS;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableExampleMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        long file_split_size = 1024 * 20 ; //20K
        String path = "/home/pauls/largeFile";
        File f = new File(path);
        long total = f.length();
        List<Future> list = new ArrayList<Future>();

        for (long index = 0; index < total; index += file_split_size){
            long _index = index;
            long _total = 0;

            if (index + file_split_size > total) {
                _total = total - index;
            } else {
                _total = file_split_size;
            }

            Future<String> x = pool.submit(new CallableExample(path, _index, _total));
            list.add(x);
        }

        pool.awaitTermination(10, TimeUnit.SECONDS);
        for(Future<String> each : list){
            System.out.println(each.get());
        }

        pool.shutdown();



    }
}
