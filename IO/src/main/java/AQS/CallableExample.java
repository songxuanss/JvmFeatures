package AQS;

import java.io.*;
import java.util.concurrent.Callable;
import static utils.IOUtil.gracefullyClose;

/**
 * Callable 是泛型接口
 * Callable 可以有线程返回值，以及获取异常
 */
public class CallableExample implements Callable<String> {
    String input;
    long index;
    long total;

    public CallableExample(String input, long index, long total) {
        this.input = input;
        this.index = index;
        this.total = total;
    }

    @Override
    public String call() {
        InputStream file = null;
        BufferedInputStream bis = null;
        long start = System.currentTimeMillis();
        try {
            file = new FileInputStream(input);
            bis = new BufferedInputStream(file);
            file.skip(this.index);
            byte[] buffer_size = new byte[256];
            int len = 0;
            int total_len = 0;

            while ((len = bis.read(buffer_size)) != -1) {
                if ((total_len + len) < total) {
                    total_len += len;
                } else {
//                    len - (total_len + len - total);
                    int sub_len = (int)total - total_len;
                    byte[] newarray = new byte[sub_len];
                    System.arraycopy(buffer_size, 0, newarray, 0, sub_len );
                    return new String(newarray);
                }
            }
            return "empty";

        } catch (IOException e) {
            e.printStackTrace();
            return "error" + e.getMessage();
        } finally {
            gracefullyClose(file);
            gracefullyClose(bis);
        }
    }
}
