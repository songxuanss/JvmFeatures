package NIO;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/*
 * 1. Channel do not store data, it has to be combined with buffer to support dev
 * 2. Channel types:
 *     java.nio.channels.channel interface:
 *      FileChannel : for file
 *      SocketChannel : for socket
 *      ServerSocketChannel : for network server
 *      DatagrameChannel : UDP
 *
 * 3. obtain channel
 *      Java classes which supports channel has getChannel method
 *      local IO:
 *          FileInputStream/FileOutputStream
 *          RandomAccessFile
 *
 *      networkIO
 *          Socket
 *          ServerSocket
 *          DatagramSocket
 *
 *      JDK 1.7 and later versions, (NIO2), static method open() can be used to obtain channel
 *      JDK 1.7 and later versions, Files class in NIO2 has method newByteChannel() method
 *
 * 4. data transfer between channels
 *
 */
public class ChannelWithMappedBuffer {

    static String inPath = "/home/pauls/java_error_in_PYCHARM_8206.log";
    static String outPath = "/home/pauls/";

    // using transferFrom and transferTo to transfer data between channel
    // Also using Direct Buffer underneath
    public void fileCopyWithChannelTransfer() throws IOException {
        FileChannel inChannel = null;
        FileChannel outChannel = null;


        inChannel = FileChannel.open(Paths.get(inPath), StandardOpenOption.READ);
        outChannel = FileChannel.open(Paths.get(outPath, "transfer"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

        inChannel.transferTo(0, inChannel.size(), outChannel);
    }

    // using direct buffer by using memory map file
    public void fileCopyWithDirectBufferMappedFile() throws IOException {
        FileChannel inChannel = null;
        FileChannel outChannel = null;

        inChannel = FileChannel.open(Paths.get(inPath), StandardOpenOption.READ);
        outChannel = FileChannel.open(Paths.get(outPath), StandardOpenOption.READ,StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        // memory mapped buffer, NOTED: same as allocateDirect!
        // directbuffer only support bytebuffer
        // no need use channel to read and write, only need to control buffer
        MappedByteBuffer inMappedBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        // setup bytes array for data read and write
        byte[] bytess = new byte[inMappedBuffer.limit()];
        inMappedBuffer.get(bytess);
        outMappedBuffer.put(bytess);

        inChannel.close();
        outChannel.close();
    }


    // using indirect buffer which is created by/in JVM
    public void fileCopyWithIndirectBuffer() {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(inPath);
            fos = new FileOutputStream(outPath);
            FileChannel inChannel = fis.getChannel();
            FileChannel outChannel = fos.getChannel();

            // Channel has to be used with Buffer
            ByteBuffer bBuffer = ByteBuffer.allocate(128);

            // step1: read bytes to bBuffer
            while (inChannel.read(bBuffer) != -1) {
                // when write, should flip to read mode, read data from buffer
                // step2: flip buffer for read
                bBuffer.flip();

                // step3: read bytes from buffer and write to outChannel
                outChannel.write(bBuffer);

                // step4: clear the buffer, and reset the limit, position
                bBuffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        ChannelWithMappedBuffer ct = new ChannelWithMappedBuffer();
        ct.fileCopyWithChannelTransfer();
    }
}
