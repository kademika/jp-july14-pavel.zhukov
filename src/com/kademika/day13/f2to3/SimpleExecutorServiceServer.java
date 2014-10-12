package com.kademika.day13.f2to3;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by Admin on 16.09.2014.
 */
public class SimpleExecutorServiceServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(8080);
//        ExecutorService pool = new ThreadPoolExecutor(0, 1000, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());
        ExecutorService pool = Executors.newFixedThreadPool(1000);
//        ExecutorService pool = Executors.newCachedThreadPool();

        while (true) {
            final Socket socket = ss.accept();
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    Utils.process(socket);
                }
            });
        }
    }
}
