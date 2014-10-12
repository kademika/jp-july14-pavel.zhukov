package com.kademika.day13.f6;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by Admin on 07.10.2014.
 */
public class NonblockingMultithreadedPoolingServer {
    private static Map<SocketChannel, Queue<ByteBuffer>> pendingData = new ConcurrentHashMap<>();
    private static Queue<SocketChannel> toWrite = new ConcurrentLinkedQueue<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("localhost", 8084));

        ssc.configureBlocking(false);
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            SocketChannel changeToWrite;

            while ((changeToWrite = toWrite.poll()) != null) {
                changeToWrite.register(selector, SelectionKey.OP_WRITE);
            }
            for (Iterator<SelectionKey> itKeys = selector.selectedKeys().iterator(); itKeys.hasNext(); ) {
                SelectionKey key = itKeys.next();
                itKeys.remove();

                if (key.isValid()) {
                    if (key.isAcceptable()) {
                        accept(key);
                    } else if (key.isReadable()) {
                        read(key);
                    } else if (key.isWritable()) {
                        write(key);
                    }

                }
            }
        }
    }

    private static void write(SelectionKey key) throws Exception {
        SocketChannel sc = (SocketChannel) key.channel();
        Queue<ByteBuffer> queue = pendingData.get(sc);

        ByteBuffer buffer;
        while ((buffer = queue.peek()) != null) {
            sc.write(buffer);
            if (!buffer.hasRemaining()) {
                queue.poll();
            } else {
                return;
            }
        }
        sc.register(key.selector(), SelectionKey.OP_READ);
    }

    private static void read(final SelectionKey key) throws IOException {
        final SocketChannel sc = (SocketChannel) key.channel();
        final ByteBuffer buffer = ByteBuffer.allocate(1024);

        int read = sc.read(buffer);
        if (read != -1) {
            pendingData.remove(sc);
            return;
        }
        pool.submit(new Runnable() {
            @Override
            public void run() {
                buffer.flip();
                for (int i = 0; i < buffer.limit(); i++) {
                    buffer.put(i, (byte) Utils.transmogrify(buffer.get(i)));
                }

                pendingData.get(sc).add(buffer);
                toWrite.add(sc);
                key.selector().wakeup();
            }
        });

//        sc.register(key.selector(), SelectionKey.OP_WRITE);
    }

    private static void accept(SelectionKey key) throws Exception {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssc.accept();
        sc.configureBlocking(false);

        sc.register(key.selector(), SelectionKey.OP_READ);
        pendingData.put(sc, new ConcurrentLinkedDeque<ByteBuffer>());
    }
}
