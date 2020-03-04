package NIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

// instead of dealing with buffer, just dealing with buffers
public class ScatterAndGather {
    static String inPath = "/home/pauls/java_error_in_PYCHARM_8206.log";
    static String outPath = "/home/pauls/";


    public void scatter() throws IOException {
        RandomAccessFile raf = new RandomAccessFile(inPath,"rw");

        FileChannel inChannel = raf.getChannel();
        ByteBuffer b1 = ByteBuffer.allocate(1024);
        ByteBuffer b2 = ByteBuffer.allocate(128);

        // Scatter Read
        ByteBuffer[] buffers = {b1, b2};
        inChannel.read(buffers);

        for (ByteBuffer each: buffers){
            each.flip();
        }

        System.out.println(new String(buffers[0].array(), 0, buffers[0].limit()));
    }

    public void gather() throws IOException {
        // Just for learning purpose, tried to use different ways to get FileChannel
        FileInputStream fis = new FileInputStream(inPath);
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = FileChannel.open(Paths.get(outPath,"gatherTest"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        ByteBuffer byteBuffer1 = ByteBuffer.allocate(128);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);

        ByteBuffer[] buffers = {byteBuffer1, byteBuffer2};

        inChannel.read(buffers);
        for (ByteBuffer b: buffers){
            b.flip();
        }
        outChannel.write(buffers);

        inChannel.close();
        outChannel.close();
    }

    public static void main(String[] args) throws IOException {
        ScatterAndGather x = new ScatterAndGather();
        x.gather();
    }
}
