package NIO;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class NIONonBlockingNetworkIO {
    static String outPath = "/home/pauls";
    static Logger logger = Logger.getLogger("nionetwork");

    public void client() throws IOException {
        // step 1: setup channel for socketconnection
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        // TODO: Step 2: change to non blocking
        sChannel.configureBlocking(false);

        // Step 3: setup buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // Step 4: send data to server
        String out = LocalDateTime.now().toString();
        System.out.println(out);
        buffer.put(out.getBytes());
        buffer.flip();
        sChannel.write(buffer);
        buffer.clear();

        // Step5: close the channel
        sChannel.close();
    }


    public void server() throws IOException {
        // Step 1: get Server Socket Channel
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        // Step 2: Setup the blocking config
        ssChannel.configureBlocking(false);

        //Step 3: binding the server port for listening
        ssChannel.bind(new InetSocketAddress(9898));

        //TODO: Step 4: GET a selector!!!!!!!!!!
        Selector localSelector = Selector.open();

        // Step 5: reigister channel to the selector
        ssChannel.register(localSelector, SelectionKey.OP_ACCEPT );

        // Step 6: keep get events which are ready on selector
        while(localSelector.select() > 0){
            // Step 7: obtain all the keys in selector
            Iterator<SelectionKey> it = localSelector.selectedKeys().iterator();

            // Step 8: check status of each key
            while(it.hasNext()){
                SelectionKey key = it.next();

                // Step 9: as we monitor all the accept event, so first check if there is a accept event
                if(key.isAcceptable()){
                    SocketChannel sChannel = ssChannel.accept();
                    sChannel.configureBlocking(false);

                    // Step 10: we register the new socket channel into the selector as well so that it can be monitored
                    sChannel.register(localSelector, SelectionKey.OP_READ);
                }

                else if (key.isReadable()){
                    // Step 11: when data come and ready to be ready by the socketchannel which we created in Step 10.
                    // we should be able to process the data
                    SocketChannel sPChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    FileChannel fChannel = FileChannel.open(Paths.get(outPath, "nioNetwork"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);
                    int len = 0;
                    while ((len = sPChannel.read(buffer)) > 0) {
                        buffer.flip();
                        fChannel.write(buffer);
                        buffer.clear();
                    }
                }

                it.remove();
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NIOBlockingNetworkIO instance = new NIOBlockingNetworkIO();
        ExecutorService es = Executors.newFixedThreadPool(1);

        es.submit(new Runnable() {
            @Override
            public void run(){
                try {
                    instance.server();
                }catch (Exception e){
                    logger.severe(e.getMessage());
                    logger.severe(e.getStackTrace().toString());
                }

            }
        });

        Thread.sleep(2000);
        instance.client();
    }

}
