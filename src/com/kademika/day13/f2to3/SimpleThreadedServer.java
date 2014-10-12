package com.kademika.day13.f2to3;

import java.net.ServerSocket;
import java.net.Socket;

public class SimpleThreadedServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(8082);
        while (true) {
            final Socket socket = ss.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Utils.process(socket);
                }
            }).start();
        }
    }
}
