package com.kademika.day13.shop.server;

import com.kademika.day13.shop.*;
import com.kademika.day13.shop.watches.Watch;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Admin on 14.10.2014.
 */
public class Server {
    private static Shop shop;

    public static void main(String[] args) throws Exception {
        TestData td = new TestData();
        Report report = new Report();

        ArrayList<Client> clients = td.arrayClients();
        ArrayList<Personal> personal = td.arrayPersonal();
        ArrayList<Watch> watches = td.arrayWatches();

        shop = new Shop(clients, personal, watches);

        getTransactionsList(shop);
        ServerSocket ss = new ServerSocket(8082);
        while (true) {
            final Socket socket = ss.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Utils.processWrite(socket, shop);
                }
            }).start();
        }
    }

    public static void getTransactionsList(Shop shop) {
        shop.setDay(Week.MONDAY.getId());
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(1), 1,
                shop.getPersonal().get(0));
        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(3), 1,
                shop.getPersonal().get(1));

        shop.setDay(Week.TUESDAY.getId());
        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(11), 2,
                shop.getPersonal().get(2));
        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(13), 1,
                shop.getPersonal().get(3));
        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(7), 1,
                shop.getPersonal().get(4));
        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(15), 1,
                shop.getPersonal().get(0));

        shop.setDay(Week.WEDNESDAY.getId());
        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(6), 1,
                shop.getPersonal().get(1));
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(17), 1,
                shop.getPersonal().get(2));

        shop.setDay(Week.THURSDAY.getId());
        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(19), 1,
                shop.getPersonal().get(3));
        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(14), 1,
                shop.getPersonal().get(4));
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(9), 1,
                shop.getPersonal().get(0));
        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(6), 1,
                shop.getPersonal().get(1));

        shop.setDay(Week.FRIDAY.getId());
        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(10), 1,
                shop.getPersonal().get(2));
        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(13), 1,
                shop.getPersonal().get(3));

        shop.setDay(Week.SATURDAY.getId());
        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(16), 1,
                shop.getPersonal().get(4));
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(8), 1,
                shop.getPersonal().get(0));
        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(4), 1,
                shop.getPersonal().get(1));
        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(3), 1,
                shop.getPersonal().get(2));
        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(17), 1,
                shop.getPersonal().get(3));
        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(9), 1,
                shop.getPersonal().get(4));

        shop.setDay(Week.SUNDAY.getId());
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(11), 1,
                shop.getPersonal().get(0));
        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(17), 1,
                shop.getPersonal().get(1));
        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(12), 1,
                shop.getPersonal().get(2));
        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(1), 1,
                shop.getPersonal().get(3));
        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(0), 1,
                shop.getPersonal().get(4));
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(0), 1,
                shop.getPersonal().get(0));
    }
}

class Utils {
    private static Shop shop;

    public static void processWrite(Socket socket, Shop shop) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("Connection from " + socket);
        try (
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                ObjectOutputStream outInFile = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
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
                    System.out.println(command);
                    if (command.equals("get data")) {
                        out.write("ok".getBytes());
                        out.write(13);
                        ArrayList<Transaction> trans = shop.getTransactionsPerDay()[4];
                        int p = 0;
                        for (Transaction tr : trans) {
                            outInFile.writeObject(tr);
                            p++;
                            if (p == 3) {
                                p = 0;
                                outInFile.flush();
                            }
                        }
//                        outInFile.writeObject(trans);
                        outInFile.flush();
//                        outInFile.close();
                    }
//                    outInFile.flush();
                } else {
                    stringBuilder.append(data + " ");
                }

            }
        } catch (IOException e) {
            System.out.println("Connection problem -" + e);
        }
    }
}
