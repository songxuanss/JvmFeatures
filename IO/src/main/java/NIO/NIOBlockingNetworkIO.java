package NIO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

/**
 * Three parts for IO:
 *      1. Channel for connection,
 *          java.nio.channels.Channel interface
 *              SelectableChannel
 *                  SocketChannel
 *                  ServerSocketChannel
 *                  DatagramChannel
 *              Pipe.SinkChannel
 *              Pipe.SourceChannel
 *
 *          Please be noted that FileChannel cannot be nonblocking.
 *          Non-Blocking are all for the network
 *
 *      2. Buffer for data read& write
 *      3. Selector for monitoring channel status
 */
public class NIOBlockingNetworkIO {
    static String inPath = "/home/pauls/java_error_in_PYCHARM_8206.log";
    static String outPath = "/home/pauls";

    public void client() throws IOException {
        // step1: get socket channel
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        // step2: allocate buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // step3: read file locally and then send to the server
        FileChannel inChannel = FileChannel.open(Paths.get(inPath), StandardOpenOption.READ);
        inChannel.read(buffer);

        buffer.flip();
        sChannel.write(buffer);

        inChannel.close();
        sChannel.close();
    }


    // Server Side: ServerSocketChannel
    public void server() throws IOException {
        // step1: setup channel. only need to call opne()
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        FileChannel outChannel = FileChannel.open(Paths.get(outPath, "serverRecvFile"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        // step2: bind the network port for listening incoming data
        ssChannel.bind(new InetSocketAddress(9898));

        // step3: get client connection
        SocketChannel sChannel = ssChannel.accept();

        // step4: allocate buffer size
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // step5: getFile from client
        while(sChannel.read(buffer) != -1){
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }

        // step6: close all resources
        sChannel.close();
        ssChannel.close();
        outChannel.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NIOBlockingNetworkIO b = new NIOBlockingNetworkIO();
        // Just start a saparate thread to run server so that client can connect.
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(new Runnable() {
            @Override
            public void run() {

                try {
                    b.server();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        sleep(1000);
        b.client();
    }
}
