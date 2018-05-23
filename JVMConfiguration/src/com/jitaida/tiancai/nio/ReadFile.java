package com.jitaida.tiancai.nio;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFile {
    /**
     * 用java NIO api拷贝文件
     *
     * @param src
     * @param dst
     * @throws IOException
     */
    public static void copyFileUseNIO(String src, String dst) {
        //声明源文件和目标文件
        FileInputStream fi = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        FileOutputStream fo = null;

        try {
            fi = new FileInputStream(src);
            fo = new FileOutputStream(dst);

            inChannel = fi.getChannel();
            outChannel = fo.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                //判断是否读完文件
                int eof = inChannel.read(buffer);
                if (eof == -1) {
                    break;
                }
                //重设一下buffer的position=0，limit=position
                buffer.flip();
                //开始写
                outChannel.write(buffer);
                //写完要重置buffer，重设position=0,limit=capacity
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inChannel.close();
                outChannel.close();
                fi.close();
                fo.close();
            }catch(IOException e){

            }
        }
    }

    public static void copyFileUseIO(String src, String dst) {
        try {
            FileInputStream in = new FileInputStream(new File(src));
            FileOutputStream out = new FileOutputStream(new File(dst));
            byte buffer[] = new byte[1024];
            while (true) {
                int data = in.read(buffer);
                if (data == -1) break;

                out.write(buffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }
    }

    public static void copyFileUseBufferWriter(String src, String dst) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(src));
            BufferedWriter out = new BufferedWriter(new FileWriter(dst));
            char buffer[] = new char[1024];
            while (true) {
                int data = in.read(buffer);
                if (data == -1) break;

                out.write(buffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }
    }

    public static void main(String args[]) {
        long start = System.currentTimeMillis();
        ReadFile.copyFileUseIO("c:\\Users\\paul_song\\src1", "c:\\Users\\paul_song\\io");
        long mid = System.currentTimeMillis();
        System.out.println("diff 1: " + (mid-start));
        ReadFile.copyFileUseNIO("c:\\Users\\paul_song\\src1", "c:\\Users\\paul_song\\nio");
        long end = System.currentTimeMillis();
        System.out.println("diff 2: " + (end-mid));
        ReadFile.copyFileUseBufferWriter("c:\\Users\\paul_song\\src1", "c:\\Users\\paul_song\\bb");
        long end2 = System.currentTimeMillis();
        System.out.println("diff 2: " + (end2-end));

    }
}
