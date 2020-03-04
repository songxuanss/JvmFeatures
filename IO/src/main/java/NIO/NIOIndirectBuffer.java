package NIO;

import java.nio.ByteBuffer;
import java.util.logging.Logger;

public class NIOIndirectBuffer {

    static Logger logger = Logger.getLogger("NIOBufferTest");

    public static void  main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);

        byteBuffer.put("this".getBytes());
        logger.info("position is: " + byteBuffer.position() + "");
        logger.info("limit is: " + byteBuffer.limit() + "");
        logger.info("mark is: " + byteBuffer.mark() + "");
        logger.info("capacity is: " + byteBuffer.capacity() + "");
        byteBuffer.flip();

        logger.info("==========================================");

        logger.info("position is: " + byteBuffer.position() + "");
        logger.info("limit is: " + byteBuffer.limit() + "");
        logger.info("mark is: " + byteBuffer.mark() + "");
        logger.info("capacity is: " + byteBuffer.capacity() + "");

        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        logger.info(new String(bytes));
    }
}

