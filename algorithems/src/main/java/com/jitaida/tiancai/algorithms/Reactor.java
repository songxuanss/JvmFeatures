package com.jitaida.tiancai.algorithms;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class Reactor {
    Socket socket = new Socket();

    public Reactor() throws Exception {
        SocketAddress socketAddress = new InetSocketAddress(1123);
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(socketAddress);

        serverChannel.configureBlocking(false);

        Selector multiplexer = Selector.open();
        serverChannel.register(multiplexer, SelectionKey.OP_ACCEPT);

        while(true){
            Iterator<SelectionKey> keys = multiplexer.selectedKeys().iterator();
            while(keys.hasNext()) {
                SelectionKey key = keys.next();
                if (key.isAcceptable()) {
                    this.acceptHandler(serverChannel, multiplexer);
                } else if (key.isReadable()){
                    this.readHandler(key, multiplexer);
                } else {
                    throw new Exception("Invalid key: " + key.toString());
                }
            }
        }
    }

    public void acceptHandler(ServerSocketChannel serverChannel, Selector selector) throws IOException {
        SocketChannel sc = serverChannel.accept();
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ);
        sc.write(Charset.forName("utf-8").encode("������������"));
    }

    public void readHandler(SelectionKey key, Selector selector) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel sc = (SocketChannel)key.channel();

        StringBuilder sb = new StringBuilder();
        while (sc.read(buffer)>0){
            buffer.flip();
            sb.append(Charset.forName("utf-8").decode(buffer));
            buffer.clear();
        }

        broadCast(selector, sc, sb.toString());
    }

    private void broadCast(Selector selector,
                           SocketChannel sourceChannel, String request) {
        /**
         * ��ȡ�������ѽ���Ŀͻ���channel
         */
        Set<SelectionKey> selectionKeySet = selector.keys();

        /**
         * ѭ��������channel�㲥��Ϣ
         */
        selectionKeySet.forEach(selectionKey -> {
            Channel targetChannel = selectionKey.channel();

            // �޳�����Ϣ�Ŀͻ���
            if (targetChannel instanceof SocketChannel
                    && targetChannel != sourceChannel) {
                try {
                    // ����Ϣ���͵�targetChannel�ͻ���
                    ((SocketChannel) targetChannel).write(
                            Charset.forName("UTF-8").encode(request));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws Exception {
        Reactor r = new Reactor();
    }
}
