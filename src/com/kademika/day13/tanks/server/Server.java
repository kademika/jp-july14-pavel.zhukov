package com.kademika.day13.tanks.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Admin on 04.11.2014.
 */
public class Server {
    private static int numConnect = 0;

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(8080);
        ExecutorService pool = Executors.newFixedThreadPool(2);

        while (true) {
            final Socket socket = ss.accept();
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    numConnect++;
                    process(socket);
//                    Utils.process(socket);
                }
            });
        }
    }

    public static void process(Socket socket) {
        if (numConnect < 3) {
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println("Connection from " + socket + " " + numConnect);
        }


//        try (
//                InputStream in = socket.getInputStream();
//                OutputStream out = socket.getOutputStream();
//        ) {
//            int data;
//            int c = 0;
//            while ((data = in.read()) != -1) {
//                c = data;
////                data = transmogrify(c);
//                stringBuilder.append(data + " ");
//
//                if (c == 13) {
//                    String str = stringBuilder.toString();
//                    for (String s : str.split(" ")) {
//                        out.write(Integer.parseInt(s));
//                    }
//                    out.write(10);
//                    out.write(13);
//                    stringBuilder = new StringBuilder();
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Connection problem -" + e);
//        }
    }
}
