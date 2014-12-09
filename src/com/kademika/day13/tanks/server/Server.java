package com.kademika.day13.tanks.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Admin on 04.11.2014.
 */
public class Server {
    private static int numConnect = 0;
    private static int firstTank;
    private static int secondTank;

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

            try (
                    InputStream in = socket.getInputStream();
                    OutputStream out = socket.getOutputStream();
            ) {
                int data;
                int c = 0;
                while ((data = in.read()) != -1) {
                    c = data;

                    if (c == 13) {
                        String str = stringBuilder.toString();
                        byte[] buf = new byte[str.length()];
                        int k = 0;
                        for (String s : str.split(" ")) {
                            if (!s.equals("10")) {
                                buf[k] = Byte.parseByte(s);
                                k++;
                            }
                        }
                        String command = new String(buf, 0, k);
                        stringBuilder = new StringBuilder();
                        System.out.println(command);
                        if (numConnect == 1) {
                            if (command.equals("start")) {
                                out.write("1_0".getBytes());
                                out.write(13);
                            }
                            if (command.equals("1")) {
                                System.out.println("First tank - defender. Wait second tank...");
                                firstTank = 1;
                            } else if (command.equals("2")) {
                                System.out.println("First tank - aggressor. Wait second tank...");
                                firstTank = 2;
                            }

                        } else {
                            if (command.equals("start")) {
                                out.write(("2_" + firstTank).getBytes());
                                out.write(13);
                            }
                            if (command.equals("1")) {
                                System.out.println("Second tank - defender");
                                secondTank = 1;
                                out.write("start the game".getBytes());
                            } else if (command.equals("2")) {
                                System.out.println("Second tank - aggressor");
                                secondTank = 2;
                                out.write("start the game".getBytes());
                            }
                        }
                    } else {
                        stringBuilder.append(data + " ");
                    }
                }
            } catch (IOException e) {
                System.out.println("Connection problem -" + e);
            }
        }


//
    }
}
