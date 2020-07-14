package zerocopy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ZeroCopy {
    String inPath;
    String outPath;

    public ZeroCopy(){
        this.inPath = System.getProperty("user.dir") + "/IO/src/main/java/zerocopy/train_sms.csv";
        this.outPath = System.getProperty("user.dir") + "/IO/src/main/java/zerocopy/output.csv";
    }

    public long useSendto() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel in = FileChannel.open(Path.of(this.inPath), StandardOpenOption.READ);
        FileChannel out = FileChannel.open(Path.of(this.outPath+0), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

        in.transferTo(0, in.size(), out);
        long end = System.currentTimeMillis();

//        System.out.println(end - start);
        return end-start;
    }

    public long useIO() throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(this.inPath);
        FileOutputStream fos = new FileOutputStream(this.outPath+1);

        byte[] buf = new byte[64*1024];
        int len = 0;
        while((len = fis.read(buf))> 0){
            fos.write(buf, 0, len);
        }
        long end = System.currentTimeMillis();

        fis.close();
        fos.flush();
        fos.close();
//        System.out.println(end - start);
        return end-start;
    }

    public long useByteBuffer() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel in = FileChannel.open(Path.of(this.inPath), StandardOpenOption.READ);
        FileChannel out = FileChannel.open(Path.of(this.outPath+2), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
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
//        System.out.println(end - start);
        return end-start;
    }

    public long useDirectByteBuffer() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel in = FileChannel.open(Path.of(this.inPath), StandardOpenOption.READ);
        FileChannel out = FileChannel.open(Path.of(this.outPath+2), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        int len = 0;
        ByteBuffer bb = ByteBuffer.allocateDirect(64*1024);
        while ((len=in.read(bb)) > 0){
            bb.flip();
            out.write(bb);
            bb.clear();
        }
        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        return end-start;
    }

    public void useMappedByteBuffer(){
        MappedByteBuffer mbb = (MappedByteBuffer) MappedByteBuffer.allocate(1024);
    }


    public static void main(String[] args) throws IOException {
        ZeroCopy z = new ZeroCopy();
        long time = 0;
        for (int i = 0; i<20; i++){
            time += z.useSendto();
        }
        System.out.println(time);

        time = 0;
        for (int i = 0; i<20; i++){
            time += z.useByteBuffer();;
        }
        System.out.println(time);

        time = 0;
        for (int i = 0; i<20; i++){
            time += z.useDirectByteBuffer();
        }
        System.out.println(time);

        time = 0;
        for (int i = 0; i<20; i++){
            time += z.useIO();
        }
        System.out.println(time);
    }
}