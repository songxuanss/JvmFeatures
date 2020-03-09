package Algorithm;

import java.io.*;

import static utils.IOUtil.gracefullyClose;

/**
 * Given a large file which contains all Integers
 * Get the most common occurance integer
 */
public class MostCommonInteger {
    public static void main(String[] args) {
        InputStream file = null;
        OutputStream fileOut = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        long start = System.currentTimeMillis();
        try {
            file = new FileInputStream("/home/pauls/largeFile");
            fileOut = new FileOutputStream("/home/pauls/output2");
            bos = new BufferedOutputStream(fileOut);
            bis = new BufferedInputStream(file);
            byte[] buffer_size = new byte[256];
            int len = 0;

            while ((len=bis.read(buffer_size)) != -1){
                bos.write(buffer_size,0 , len);
                bos.flush();
            }

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            gracefullyClose(file);
            gracefullyClose(fileOut);
            gracefullyClose(bos);
            gracefullyClose(bis);
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}

