package io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;

public class BufferedInputStreamTest extends BaseTest {


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        FileInputStream fis = null;
        BufferedInputStream bis = null;

        byte[] buffer = new byte[256];

        try {
            fis = new FileInputStream(testFile);
            bis = new BufferedInputStream(fis);

            int cnt = 0;
            while ((cnt = bis.read(buffer)) != -1) {
                // maybe bufferedOutputStream
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("The time used :" + (end - start));
    }
}
