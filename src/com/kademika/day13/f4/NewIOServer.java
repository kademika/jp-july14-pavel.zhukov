package com.kademika.day13.f4;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Admin on 23.09.2014.
 */
public class NewIOServer {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("localhost", 8084));

        ExecutorService pool = Executors.newFixedThreadPool(1000);

        while (true){
            final SocketChannel socketChannel = ssc.accept();
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    Utils.process(socketChannel);
                }
            });
        }
    }
}
