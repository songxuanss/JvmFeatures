package zerocopy;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ZeroCopyWithSocketIO {
    String inPath;
    String outPath;
    final String host = "47.108.140.102";
    final int port = 1314;

    public ZeroCopyWithSocketIO(){
        this.inPath = System.getProperty("user.dir") + "/IO/src/main/java/zerocopy/test_sms.csv";
    }

    public long useSendto() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel in = FileChannel.open(Path.of(this.inPath), StandardOpenOption.READ);
        SocketChannel out = SocketChannel.open(new InetSocketAddress(host, port));

        System.out.println(in.size());

        in.transferTo(0, in.size(), out);
        long end = System.currentTimeMillis();

        System.out.println(end - start);
        return end-start;
    }

    public long useIO() throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(this.inPath);
        Socket socket = new Socket(host, port);
        OutputStream fos = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(fos, 64 * 1024);

        byte[] buf = new byte[64 * 1024];
        int len = 0;
        while((len = fis.read(buf))> 0){
            bos.write(buf, 0, len);
            bos.flush();
        }
        long end = System.currentTimeMillis();

        fis.close();
        bos.close();
        fos.close();
        System.out.println(end - start);
        return end-start;
    }

    public long useByteBuffer() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel in = FileChannel.open(Path.of(this.inPath), StandardOpenOption.READ);
        SocketChannel out = SocketChannel.open(new InetSocketAddress(host, port));
        int len = 0;
        ByteBuffer bb = ByteBuffer.allocate(64*1024);
        while ((len=in.read(bb)) > 0){
            bb.flip();
            out.write(bb);
            bb.clear();
        }
        in.close();
        out.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return end-start;
    }

    public long useDirectByteBuffer() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel in = FileChannel.open(Path.of(this.inPath), StandardOpenOption.READ);
        SocketChannel out = SocketChannel.open(new InetSocketAddress(host, port));
        ByteBuffer bb = ByteBuffer.allocateDirect(64*1024);
        while (in.read(bb) > 0){
            bb.flip();
            out.write(bb);
            bb.clear();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return end-start;
    }

    public void useMappedByteBuffer(){
        MappedByteBuffer mbb = (MappedByteBuffer) MappedByteBuffer.allocate(1024);
    }


    public static void main(String[] args) throws IOException {
        ZeroCopyWithSocketIO z = new ZeroCopyWithSocketIO();
//        long time = 0;
//        for (int i = 0; i< 20; i++){
//            time += z.useSendto();
//        }
//        System.out.println(time);
//
//        time = 0;
//        for (int i = 0; i<20; i++){
//            time += z.useByteBuffer();;
//        }
//        System.out.println(time);
//
//        time = 0;
//        for (int i = 0; i<20; i++){
//            time += z.useDirectByteBuffer();
//        }
//        System.out.println(time);
//
//        time = 0;
//        for (int i = 0; i<20; i++){
//            time += z.useIO();
//        }
//        System.out.println(time);

        z.useIO();
//        z.useDirectByteBuffer();
    }
}