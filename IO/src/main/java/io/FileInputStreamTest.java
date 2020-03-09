package io;

import java.io.*;
import java.util.Calendar;

public class FileInputStreamTest extends BaseTest{

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        byte[] buffer = new byte[256];

        try {
            fis = new FileInputStream(testFile);
            fos = new FileOutputStream(outputFile);
            int cnt = 0;

            while((cnt = fis.read(buffer)) != -1){
                // maybe bufferedOutputStream
                fos.write(buffer, 0, cnt);
                fos.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("The time used :" + (end-start));
    }
}
